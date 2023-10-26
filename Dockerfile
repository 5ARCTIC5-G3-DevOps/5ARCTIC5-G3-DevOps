FROM openjdk:11
EXPOSE 8080
ADD target/BACKEND.jar BACKEND.jar
ENTRYPOINT ["java","-jar","/BACKEND.jar"]