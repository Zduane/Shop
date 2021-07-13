#Shopping-Application

This Java application will allow users to retrieve amount of products sold per day, week, or month, retrieve orders for specific customer, and create orders.

#Developer

Zachary Warren

#Dependencies

JDK 1.8 or better Order data is stored inside orders.json file under resources folder

Installation
Download Zip file
Unzip file
Download Intellij or IDE of choice
Open IDE
Import project as existing Maven project
To run, build project and run local server inside IDE
Use Postman Collections provided in ZippedFile to sample the output with sample requests
Import Collection "CodingChallenge- Shop Application.postman_collection.json" into POSTMAN
Make sure Url Port in POSTman matches Port running in local server (http:localhost:8080/)
Each Request contains endpoint and request info needed to communicate with application
Frameworks
Spring Boot Maven Mockito Junit

#Libraries

lombok gson

#Testing To run unit tests, right click project folder and click Run All Tests

#Security Secured each endpoint with WebSecurityConfig

#If-More-Time-Was-Available

Would have created more thorough J Unit test cases
Would have more custom exception handling
Would have added more data to Json file
#Challenges -Updating the Json file according to orders created is a challenge becuse jar files are read only -Json file is updated if json file is in your local directory -Storing data without database
