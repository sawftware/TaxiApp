# TaxiApp
### TaxiApp take-home coding assessment

## Requirements / Technologies
- Project requires JDK 1.8 (1.8.0_402)
- MVN v3 (3.9.6)
- Spring Boot v2 (2.1.0.RELEASE)
- Spring v5 (v5.1.2.RELEASE)

## How to start the application
```mvn package && java -jar target\TaxiApp-0.0.1-SNAPSHOT.jar```

## How to build the project and run the unit tests
```mvn clean install```

## Default homepage:port - default user login: 
- http://localhost:8080/ - admin/admin

## Swagger UI
- http://localhost:8080/swagger-ui.html

## H2 Database
- http://localhost:8080/h2-console/ (sa/password)

## Requirements
- [x] List all taxis with their status and location
- [x] Publish a new booking to all available taxis
- [x] Allow taxis to set their state (available or booked)
- [x] Implement a dashboard or statistics regarding the bookings
- [x] Well-structured, clean and comprehensible
- [x] Use of software design patterns and object-oriented implementations
- [x] Documentation and UML Diagrams
- [x] Containerization and README
- [x] Meaningful Unit Testing / Integration Testing
- [x] 90% Code Coverage

## Screenshots / User Guide
![](https://github.com/AlanKavo92/TaxiApp/blob/master/Swagger-API.PNG)