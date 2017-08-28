## Java CI/CD App Boilerplate

This app is created by the standard Maven Webapp archetype with Spring REST support added. 

Distribution support according to the Continuous Integration/Delivery principle and containerized with Docker. 

## Runtime Requirements

- JDK 1.8
- Maven 3.X
- Tomcat 7+

## Unit Tests
All unit tests are located in the directory `src/test/java` and do not need any further setup. 
- `mvn test` - Runs the unit tests

## Integration Tests
This app uses [Selenium](http://www.seleniumhq.org) for API client testing over Http. To be able to run the integration test the web driver has to be set in the file `Selenium.java` located in `src/integration-test/java/com/miskowskij/http/`.

- `mvn integration-test` - Runs the integration tests in directory `src/integration-test/java`
- `mvn verify` - Runs all tests

## Eclipse IDE Support
- `mvn eclipse:eclipse` - Adds Eclipse Dynamic Web support

## Techniques used

- [Docker](http://www.docker.com)
- [CircleCI](http://circleci.com)
- [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/)
- [SeleniumHQ](http://seleniumhq.org)
