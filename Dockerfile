FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
ADD target/currency-api-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
