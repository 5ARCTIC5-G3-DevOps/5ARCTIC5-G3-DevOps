FROM openjdk:11
EXPOSE 8080
ADD target/DEVOPS.jar DEVOPS.jar
ENTRYPOINT ["java","-jar","/DEVOPS.jar"]