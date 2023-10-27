FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} 5ARCTIC5-G3-DevOps.jar
ENTRYPOINT ["java","-jar","/5ARCTIC5-G3-DevOps.jar"]
EXPOSE 8082