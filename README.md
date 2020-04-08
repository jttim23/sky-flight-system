# Sky-flight-system

## Table of contents
* [General info](#general-info)
* [Technologies](#used-technologies)
* [Setup](#setup)

## General info
A job interview project to build a simple space-flights reservation system using **Spring Boot** and **Spring Data JPA** CRUD operations.
All Endpoints are availeble under this link(Swagger to be added)
### The general requirements and constraints were as follows:
#### Structure:
1. Flight:
* Arrival Date and Time
* Departure Date and Time
* Seat Quantity
* Ticket Price
* List of Tourists
2. Tourist:
* Name
* Last Name
* Gender
* Country
* Notes
* Date of Birth
* List of Flights 
#### Functionality:
1. Flights Management:
* Display list of all flights
* Display flight with all its tourists
* Delete flight
* Create new flight
* Add tourist to the flight
* Delete tourist from the flight
2. Tourists Management:
* Display list of all tourists
* Display tourist with all its flights
* Delete tourist
* Create new tourist
* Add flight to the tourist
* Delete flight from the tourist
## Used Technologies
Project is created with:
* Spring
* Mockito
* Project Lombok
* Junit5
* Rest-Assured
* Hibernate
* Swagger 	
## Setup
####Sky-flight-system is a Spring Boot application built using Maven. You can build a jar file and run it from the command line:
##### git clone https://github.com/jttim23/sky-flight-system.git
##### cd sky-flight-system
##### mvn package
##### java -jar target/*.jar
