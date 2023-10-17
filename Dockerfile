FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} devops-docker.jar
ENTRYPOINT ["java","-jar","/devops-docker.jar"]
EXPOSE 8083