# TaxiApp
### TaxiApp take-home coding assessment

## Requirements / Pre-requisites
- Application requires JDK 1.8 (1.8.0_402)
- Application requires MVN v3 (3.9.6)

## Technologies (Maven External Dependencies)
- Application uses Spring Boot (Version 2.1.0.RELEASE)
- Application uses Spring (Version 5.1.2.RELEASE)
- Application uses Thymeleaf (Version 3.0.11.RELEASE)
- Application uses H2 Database (Version 1.4.197)
- Application uses Swagger (Version 2.9.2)
- Application uses JUnit (Version 4.12)

## How to start the application
```mvn package && java -jar target\TaxiApp-0.0.1-SNAPSHOT.jar```

## How to build the project and execute the unit tests
```mvn clean install```

## Default homepage and login: 
- http://localhost:8080/ - admin/admin

## Swagger UI
- http://localhost:8080/swagger-ui.html

## H2 Database
- http://localhost:8080/h2-console/ (sa/password)

## Unit Test Coverage - 90%
![](https://github.com/sawftware/TaxiApp/blob/main/readme-img/UnitTestCoverage.png)


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
![](https://github.com/sawftware/TaxiApp/blob/main/readme-img/Login.png)

![](https://github.com/sawftware/TaxiApp/blob/main/readme-img/InsertBooking.png)

![](https://github.com/sawftware/TaxiApp/blob/main/readme-img/RegisterTaxi.png)

![](https://github.com/sawftware/TaxiApp/blob/main/readme-img/DisplayBookings.png)

![](https://github.com/sawftware/TaxiApp/blob/main/readme-img/DisplayTaxis.png)

![](https://github.com/sawftware/TaxiApp/blob/main/readme-img/TaxiDashboard.png)

![](https://github.com/sawftware/TaxiApp/blob/main/readme-img/AdminDashboard.png)
