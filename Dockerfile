FROM openjdk:11

WORKDIR /app
COPY target/*.jar spirng.jar

EXPOSE 8089

CMD ["java", "-jar", "app.jar"]

