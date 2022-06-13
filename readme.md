<h1>Test Automation Seminar | Coding School</h1>
Instructor: Vasilis Petrou

<h3>PREREQUISITE SOFTWARE</h3>

Below software is needed to be downloaded and installed prior the practice on Test Automation: 

- Java JDK (11 or later) https://www.oracle.com/java/technologies/javase-downloads.html
- IntelliJ IDEA CE https://www.jetbrains.com/idea/download 
- Apache Maven https://maven.apache.org/download.cgi  
- Google Chrome https://www.google.com/chrome  

<h3>TEST AUTOMATION PLAYGROUND</h3>

Test Automation Playground is a CRUD (Create,Read,Update,Delete) Application with UI & Rest WebServices.
<br> Technologies used for the Test Automation Playground: Java, Spring Boot, H2 DB, Angular 6 with PrimeNG and Material Components.
<br>Main goal of this application is to be used for practice on Test Automation. 

Following below steps, we can build the Test Automation Playground application:

1. Build


    mvn clean install

2. Run
   

    mvn spring-boot:run

Alternatively, after build a JAR file is generated under /target/releases. 
Running below command application will be up and running.
   
    cd target/releases
    java -jar test-automation-1.0.0.jar

<h3>TEST AUTOMATION PLAYGROUND LINKS</h3>

Web Application URL: http://localhost:7001/

API URL: http://localhost:7001/api/v1

Swagger URL (REST API Documentation): http://localhost:7001/swagger-ui.html


<h3>H2 DATABASE</h3>

Test Automation Playground uses H2 Database. When application is stopped, data are lost. 

Console: http://localhost:7001/h2-console
    
    JDBC URL: jdbc:h2:mem:testdb
    User Name: sa
    Password: password


