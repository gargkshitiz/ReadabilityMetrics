# Readability web app and REST services

## Features: 
- Text analysis 
- Learn complex words for future 


## Tech stack: 
- Java
- Spring MVC
- Spring Data JPA
- Hibernate
- Bootstrap
- JQuery
- AJAX
- JSP
- Swagger (for REST API documentation)
- Jackson, Gson
- Maven
- Junit

## Prerequisites
- JDK 1.8+
- Maven 3+

## Compile, build and deploy 
- Pull this project from github (URI-https://github.com/gargkshitiz/readabilityMetrics.git) onto the desired machine, and import it in your IDE as a maven project 
- Running 'mvn clean install' from within readabilityMetrics folder would create readability.war inside exchange-rate\target folder.
- On the deployment/local machine:  
-----------------------------------  
	- Just run 'java -jar readability.war'

## Configuration
- Web application is configured as per src\main\resources\application.properties. 
- If required, this file can be externalized.

## Screen/Features

### Demo page, /readability/demo
- Demonstrates text analysis and machine learning for a complex word

### REST API, /readability/api/learnComplexWord/{word}
- Documented in detail at /readability/swagger-ui.html

## Swagger URI
http://localhost:2222/readability/swagger-ui.html

## Demo URL
http://localhost:2222/readability/demo