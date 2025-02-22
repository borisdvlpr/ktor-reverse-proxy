FROM golang:1.24-bookworm
WORKDIR /app

COPY go.mod ./
RUN go mod download

COPY . .
RUN go build -o go-service

EXPOSE 8000
CMD ["/app/go-service"]
