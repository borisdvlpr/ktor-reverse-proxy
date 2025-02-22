package com.borisdvlpr

import java.io.BufferedReader
import java.io.InputStreamReader

fun getDockerServices(): Map<String, String> {
    val serviceMap = mutableMapOf<String, String>()
    val hostPortRegex = Regex("(\\d+)->\\d+/tcp")

    try {
        val process = ProcessBuilder("docker", "ps", "--format", "{{.Names}} {{.Ports}}").start()
        BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
            reader.forEachLine { line ->
                val parts = line.split(" ", limit = 2)
                if (parts.size == 2) {
                    val serviceName = parts[0]
                    val portsInfo = parts[1]
                    val hostPort = hostPortRegex.find(portsInfo)?.groups?.get(1)?.value
                    if (hostPort != null) {
                        serviceMap[serviceName] = "http://localhost:$hostPort/"
                    }
                }
            }
        }

    } catch (e: Exception) {
        println("Error occurred while fetching Docker services: ${e.message}")
    }

    println("Service map: $serviceMap")
    return serviceMap
}
