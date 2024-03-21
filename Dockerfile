FROM lippert/adoptopenjdk-17
LABEL authors="ignac"
VOLUME /temp
WORKDIR ./app
COPY target/duxsoftware-1.0-SNAPSHOT.jar /app.jar
COPY /src/main/resources/application.yml /src/main/resources/futbol_teams.sql ./app/
COPY /src/test/java/org/example /tests
ENV TEST_DIR /tests
CMD java -cp ".:/app/app.jar" org.junit.runner.JUnitCore $(find /tests -name "*Test.class")
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]