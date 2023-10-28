FROM openjdk:11
ADD target/*.jar 5ARCTIC5-G3-DevOps.jar
ENTRYPOINT ["java","-jar","/5ARCTIC5-G3-DevOps.jar"]
EXPOSE 8082