# Java CI/CD App Boilerplate

This app is created by the standard Maven Webapp archetype with Spring REST support added. 

AWS distribution support according to the Continuous Integration/Delivery principle using Docker.

## Runtime Requirements

- JDK 1.8
- Maven 3.X
- Tomcat 7+

## Testing

Supports both Unit and Integration Tests using Maven Surefire and Failsafe plugins.

- `mvn verify` - Runs all tests

### Run Unit Tests

All unit tests are located in the directory `src/test/java` and do not need any further setup. 
- `mvn test` - Runs the unit tests

### Run Integration Tests
This app uses [Selenium](http://www.seleniumhq.org) for API client testing over Http. To be able to run the integration test the path of the local web driver has to be set in `Selenium.java` located in `src/integration-test/java/com/miskowskij/http/`.

Following commands will initiate 
- `mvn integration-test` - Runs the integration tests in directory `src/integration-test/java`

## Eclipse IDE Support
- `mvn eclipse:eclipse` - Adds Eclipse Dynamic Web support

## Techniques used

- [Docker](http://www.docker.com)
- [CircleCI](http://circleci.com)
- [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/)
- [SeleniumHQ](http://seleniumhq.org)
