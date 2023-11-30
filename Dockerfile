FROM amazoncorretto:17
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java", "-git pulljar", "app.jar"]