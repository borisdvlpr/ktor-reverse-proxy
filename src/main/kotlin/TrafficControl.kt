package com.borisdvlpr

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val blockedEndpoints = mutableSetOf<String>()

fun Application.configureTrafficControl() {
    routing {
        post("proxy/block") {
            val endpoint = call.receive<String>()
            blockedEndpoints.add(endpoint)
            call.respond(HttpStatusCode.OK, "Blocked $endpoint")
        }

        post("proxy/unblock") {
            val endpoint = call.receive<String>()
            blockedEndpoints.remove(endpoint)
            call.respond(HttpStatusCode.OK, "Blocked $endpoint")
        }

        get("/proxy/blocked") {
            call.respond(HttpStatusCode.OK, blockedEndpoints.joinToString(", "))
        }
    }
}