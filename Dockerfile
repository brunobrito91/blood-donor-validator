FROM openjdk:11
COPY target/blood-donor-validator-0.0.1-SNAPSHOT.jar blood-donor-validator-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/blood-donor-validator-0.0.1-SNAPSHOT.jar"]
