FROM openjdk:11-ea-11-jdk
COPY target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=deploy","/config-server-0.0.1-SNAPSHOT.jar"]
