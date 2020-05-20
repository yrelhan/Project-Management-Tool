# What is the Project
![Project Management Tool Homepage](https://i.ibb.co/wpKt0vZ/PPMT.png)
This App is a ReactJS and Springboot App which allows the user to manage projects

# Stack

1.  ReactJS
-   React Components
-   HTTP Client using axios Library
-   Basic react Routing
-   React Bootstrap
-   Communication between React Components

2.  SpringBoot 2
-   Springboot RestController
-   Service, Repository(DAO) from Spring Framework
-   Autowiring is used as well to inject the Dependencies

3. JSON Web Tokens for Authentication

# Live Demo
[https://yatins-project-management-tool.herokuapp.com/](https://yatins-project-management-tool.herokuapp.com/)

# Prerequites to Run the Application

### Install NodeJS

Refer  [https://nodejs.org/en/](https://nodejs.org/en/)  to install NodeJS

### Install create-react-app

Install create-react-app npm package globally. This will help to easily run the project and also build the source files easily. Use the following command to install create-react-app

*npm install -g create-react-app*

### Install maven

Install Maven and Ensure IDE is pointing to Right Maven folder

Also Ensure maven is set as a path variable to that maven commands can be run easily

Refer  [https://maven.apache.org/](https://maven.apache.org/)  for maven installation

# Steps to Run the Application

Clone the repo into local

Open the [ppmtool-react-client](https://github.com/yrelhan/Project-Management-Tool/tree/master/ppmtool-react-client "ppmtool-react-client") folder and install the npm packages using the following commands

*npm install*

Go Back to the Parent Project Folder and build the package using the following commands

cd ..
mvn clean package

Go to [project_management_tool](https://github.com/yrelhan/Project-Management-Tool/tree/master/project_management_tool "project_management_tool") folder and start the Application using the following commands

*mvn spring-boot:run*

The Application runs on  **localhost:8080**  and the application runs in a embedded container in local

# Folder Structure

**ppmtool-react-client**  : This has the Client Code implemented using React JS

**project_management_tool**  : This has the Springboot code

**pom.xml**  : This is multimodule pom. This pom in turn executes the pom within the client and the server folders

# Application Design

### ReactJS

#### [](https://github.com/aditya-sridhar/springboot2-reactjs-template#components)Components

1.  **Project**  Component : This Component allows us to add and update projects
    
2.  **Project Board**  Component : This Component allows us to add and update project tasks and backlogs and takes care of updating the project board. 
3. **User Management**  Component : This Component takes care of registering and loggin-in a user.


#### HTTP client

**axios**  library is used to make HTTP Calls

The application has just one url /customerlist which ties to  _Customers_  Component

### SpringBoot

The package  `com.example.project_management_tool`  has the  `ProjectManagementToolApplication.java`  file which ensures that the application runs in an embedded container and forms the starting point of the code

 1. **Domains**

The package  `com.example.project_management_tool.domain` defines all the Java models for the project. 
 - Backlog
 - Project
 - ProjectTask
 - User

2. **Exceptions**
The package  `com.example.project_management_tool.exceptions`  has the custom exception logic and response logic defined for the following exceptions.
 - Custom Response Entity Exception
 - Invalid Login Response
 - Project Id Exception
 - Project Not Found Exception
 - UserName already exists Exception

3. **Repositories**
The package  `com.example.project_management_tool.repositories`  has the repositories for CRUD operations defined. 
 - Backlog Repository
 - Project Repository
 - Project Task Repository
 - User Repository

4. **Services**
The package  `com.example.project_management_tool.services`  has the services defined and has the logic for the below tasks. The  **Service**  is where the business logic is run on the Data which comes from DAO
 - Custom User Details Service
 - Map Validation Error Service
 - Project Service
 - Project Task Service
 - User Service

5. **Controllers**
The package  `com.example.project_management_tool.web`  has the Rest Controller defined. The controller has all the end points defined and mentions which function should be executed when an end point is called. The Controller also defines which Endpoint Calls which Service. 
 - Backlog Controller
 - Project Controller
 - User Controller

6. **Payload**
The package  `com.example.project_management_tool.payload`  handles login request and the JWT login response.


The  `application.properties`  file is used to define various properties such as the port in which the embedded container runs , the context path of the application etc

##### Note
Uncomment lines for H2 in memery database if you don't have MySQL database installed (and subsequently comment lines for MySQL db)

# References

**create-react-app**  : The following link has all the commands that can be used with create-react-app  [https://github.com/facebook/create-react-app](https://github.com/facebook/create-react-app)

**ReactJS**  : Refer to  [https://reactjs.org/](https://reactjs.org/)  to understand the concepts of ReactJS

**React Bootstrap**  : Refer to  [https://react-bootstrap.github.io/getting-started/introduction/](https://react-bootstrap.github.io/getting-started/introduction/)  to understand how to use React Bootstrap

**Springboot**  : Refer to  [https://spring.io/guides/gs/rest-service/](https://spring.io/guides/gs/rest-service/)  to build REST service using Springboot

**axios**  : Refer to  [https://www.npmjs.com/package/axios](https://www.npmjs.com/package/axios)  to know more about how to use axios library to make http requests

**JSON Web Tokens** Refer to  [https://jwt.io/](https://jwt.io/) for more information about JSON Web Tokens.
