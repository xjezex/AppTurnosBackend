FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/AppTurnos-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 5005

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]

