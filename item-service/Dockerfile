FROM openjdk:11-ea-11-jdk
COPY target/item-service-0.0.1-SNAPSHOT.jar item-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=deploy","/item-service-0.0.1-SNAPSHOT.jar"]
