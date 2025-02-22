FROM golang:1.23-bookworm
WORKDIR /app

COPY go.mod ./
RUN go mod download

COPY . .
RUN go build -o k8s-web

EXPOSE 8000
CMD ["/app/k8s-web"]
