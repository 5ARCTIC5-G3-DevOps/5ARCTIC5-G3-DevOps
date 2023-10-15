FROM openjdk:11
EXPOSE 8083
ADD target/devops-docker.jar devops-docker.jar
ENTRYPOINT ["java","-jar","/devops-docker.jar"]