FROM openjdk:16
ADD target/autorent.jar autorent.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "autorent.jar"]