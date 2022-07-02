FROM openjdk:17
ADD target/KBE-Warehouse-0.0.1-SNAPSHOT.jar KBE-Warehouse-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "KBE-Warehouse-0.0.1-SNAPSHOT.jar"]