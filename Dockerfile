FROM docker.io/library/gradle:8.2-jdk17

WORKDIR /app
COPY . ./
RUN gradle build
CMD ["gradle", "run"]
EXPOSE 8080
