# Shipt-Coding-Challenge-Shopping-Application

This Java application will allow users to retrieve amount of products sold per day, week, or month, retrieve orders for specific customer,
and create orders.

#Developer

Zachary Warren

#Dependencies

JDK 1.8 or better
Order data is stored inside orders.json file under resources folder


## Installation

1. Download Zip file
2. Unzip file
3. Download Intellij or IDE of choice
4. Open IDE
5. Import project as existing Maven project
6. To run, build project and run local server inside IDE
7. Use Postman Collections provided in ZippedFile to sample the output with sample requests
8. Import Collection "CodingChallenge- Shop Application.postman_collection.json" into POSTMAN   
9. Make sure Url Port in POSTman  matches Port running in local server (http:localhost:8080/)
10. Each Request contains endpoint and request info needed to communicate with application

## Frameworks

Spring Boot
Maven
Mockito
Junit

#Libraries

lombok
gson

#Testing
To run unit tests, right click project folder and click Run All Tests

#Security
Secured each endpoint with WebSecurityConfig

#If-More-Time-Was-Available
- Would have created more thorough J Unit test cases
- Would have more custom exception handling
- Would have added more data to Json file


#Challenges
-Updating the Json file according to orders created is a challenge becuse jar files are read only
-Json file is updated if json file is in your local directory
-Storing data without database
