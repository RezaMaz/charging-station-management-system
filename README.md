# charging station management system
manage charging stations, charging processes and customers

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the `main` method in the `com.htb.chargingstationmanagementsystem.ChargingStationManagementSystemApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
./mvnw clean install 
./mvnw spring-boot:run
```
The web application is accessible via localhost:8080

you can also run with Docker and the benefit of it is you don't need JDK17 preinstalled on your system.
do this instructions:
```shell
docker build --tag=csms:latest .
docker run -d -p 8080:8080 csms:latest
```

## Running the testsuite locally
```shell
./mvnw test
```

## Request Sample
- http://localhost:8080/v1/rate (POST Request)
- body example:
  {
    "rate": { "energy": 0.3, "time": 2, "transaction": 1 },
    "cdr": { "meterStart": 1204307, "timestampStart": "2021-04-05T10:04:00Z", "meterStop": 1215230, "timestampStop": "2021-04-05T11:27:00Z" }
  }
- Basic Authentication for access resources: username: root, password: root

## Swagger Link
http://localhost:8080/swagger-ui/index.html
 