FROM openjdk:11-ea-11-jdk
COPY target/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","/user-service-0.0.1-SNAPSHOT.jar"]
