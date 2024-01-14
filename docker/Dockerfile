# For Java 17, try this
FROM openjdk:17-alpine
ARG jar
COPY ${jar} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
