FROM amazoncorretto:17
CMD ["./mvnw", "clean", "package"]
VOLUME /tmp
<<<<<<< HEAD
ARG JAR_FILE=build/libs/*.jar
=======
ARG JAR_FILE=./build/libs/*.jar
>>>>>>> main
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

