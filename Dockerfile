FROM lippert/adoptopenjdk-17
LABEL authors="ignac"
VOLUME /temp
WORKDIR ./app
COPY target/duxsoftware-1.0-SNAPSHOT.jar /app.jar
EXPOSE 8080
COPY /src/main/resources/application.yml ./app
ENTRYPOINT ["top", "-b", "java", "-jar", "/app.jar"]