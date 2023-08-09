# Build stage
FROM --platform=${BUILDPLATFORM} docker.io/library/eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app
COPY . ./
RUN ./gradlew shadowJar

# Runtime
FROM --platform=${TARGETPLATFORM} docker.io/library/eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY --from=builder /app .
CMD ["./gradlew", "run"]
EXPOSE 8080
