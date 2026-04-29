# Tech Challenger - Grupo80

A small Spring Boot REST API project for the FIAP tech challenger (group 80).

This repository contains a Java Spring Boot application built with Maven, configured to run with Docker and Docker Compose. It uses PostgreSQL as the primary database.

Tech stack
- Java 21 
- Spring Boot
- Maven
- PostgreSQL
- Docker / Docker Compose

Running with Docker Compose

Prerequisites
- Docker (https://docs.docker.com/get-docker/)
- Docker Compose v2 or higher (often provided with modern Docker installs)

1) Create a `.env` file in the project root with the required environment variables, based on `.env.example`. Example:

```bash
# .env (example)
SERVER_PORT=8080
POSTGRES_DB=techdb
POSTGRES_USER=techuser
POSTGRES_PASSWORD=techpass
```

2) Build and start the application and the database using Docker Compose:

```bash
# build and start in foreground (shows logs)
docker compose up --build

# or run in detached mode
docker compose up --build -d
```

3) Verify the application is available. By default the compose file maps the container port to the host at the address 127.0.0.1 and the port defined in `SERVER_PORT`:

- URL: http://127.0.0.1:8080/ (replace 8080 with your `SERVER_PORT` value)

Notes
- The `docker-compose.yml` in this repo builds the application image from the `Dockerfile` at the project root. If you need to change the exposed port, update the `SERVER_PORT` in your `.env` and the application `application-docker.properties` if required.
- PostgreSQL data is persisted using a Docker volume (`postgres-data`) defined in `docker-compose.yml`.
- The compose configuration waits for PostgreSQL to become healthy before starting the app using a `depends_on` health check.

Troubleshooting
- If containers fail to start, view logs with:

```bash
docker compose logs -f
```

- To force a rebuild (ignore cache):

```bash
docker compose build --no-cache
```

- To stop and remove containers, networks and volumes created by compose:

```bash
docker compose down
```

If you'd like, I can also add a small `env.example` file to the repo, or verify that the `Dockerfile` exposes and uses the `SERVER_PORT` environment variable—tell me which you'd prefer next.

4) To reset the database, you can stop the containers and remove the volume:

```bash
docker compose down -v
```

- Or run the following command to remove just the volume:

```bash
docker volume ls
docker volume rm <volume-name>
```

- You may need to delete the containers:

```bash
docker ps -a
docker rm <container_id>
```