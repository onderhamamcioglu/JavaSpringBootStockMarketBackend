FROM openjdk:18-slim
LABEL authors="Önder Hamamcıoğlu"
COPY target/MidasCodeChallange-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]