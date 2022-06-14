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

1. Build (without tests)


    mvn clean install -DskipTests

2. Run Unit Tests


    mvn test

3. Generate Unit Test Report


    mvn surefire-report:report-only
    mvn site -DgenerateReports=false 

<i>* Report is generated in /target/site/surefire-report.html</i>

4. Run Integration Tests


    mvn failsafe:integration-test

5. Generate Integration Test Report


    mvn surefire-report:failsafe-report-only
    mvn site -DgenerateReports=false 

<i>* Report is generated in /target/site/failsafe-report.html</i>

<b>Instead of 2,3,4,5</b></br>
<b>Run both Unit & Integration Tests and Generate Unit & Integration Test Report and Code Coverage Report (Jacoco)</b>


    mvn integration-test
    mvn surefire-report:report-only
    mvn surefire-report:failsafe-report-only
    mvn site -DgenerateReports=false     

<i>** Code Coverage Report is generated in /target/site/jacoco/index.html</i>

6. Run Application


    mvn spring-boot:run

Alternatively, after build a JAR file is generated under /target/releases. 
Running below command application will be up and running.
   
    cd target/releases
    java -jar test-automation-1.0.0.jar

<i>To stop 'test-automation-playground' just click CTRL + C on the console window.</i>

7. Run Acceptance Tests (API)


    mvn verify -DskipTests -Dskip.acceptance.tests=false

<i>Report is generated in /target/cucumber/cucumber-html-reports/overview-features.html</i>


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


