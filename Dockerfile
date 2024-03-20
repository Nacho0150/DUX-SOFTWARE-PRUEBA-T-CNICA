FROM lippert/adoptopenjdk-17
LABEL authors="ignac"
VOLUME /temp
WORKDIR ./app
COPY target/duxsoftware-1.0-SNAPSHOT.jar /app.jar
COPY /src/main/resources/application.yml ./app
COPY /src/main/resources/futbol_teams.sql ./app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]