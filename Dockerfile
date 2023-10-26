FROM openjdk:11
EXPOSE 8083
ADD target/*.jar BACKEND.jar
ENTRYPOINT ["java","-jar","/BACKEND.jar"]