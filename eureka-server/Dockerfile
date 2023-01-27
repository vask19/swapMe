FROM openjdk:11-ea-11-jdk
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=deploy","/eureka-server-0.0.1-SNAPSHOT.jar"]
