FROM openjdk:11
EXPOSE 8082
ADD target/*.jar BACKEND.jar
ENTRYPOINT ["java","-jar","/BACKEND.jar"]