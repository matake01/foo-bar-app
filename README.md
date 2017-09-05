# Java CI/CD App Boilerplate

This app is created by the standard Maven Webapp archetype with Spring REST support added. 

Distributed at AWS under the Continuous Integration + Delivery principle.

Following command will build and package the project:

```sh
mvn clean install
```

## Prerequisites

- JDK 1.8
- Maven 3.X

## Techniques used

- [Spring REST Service](https://spring.io/guides/gs/rest-service/)
- [Docker](http://www.docker.com)
- [CircleCI](http://circleci.com)
- [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/)
- [SeleniumHQ](http://seleniumhq.org)

## Testing

Supports both Unit and Integration Tests using Maven Surefire and Failsafe plugins.

Verify and run all tests:

```sh
mvn verify
```

#### Run Unit Tests

Runs the unit tests located in the directory `src/test/java`:

```sh
mvn test
```

#### Run Integration Tests
This boilerplate project uses [Selenium](http://www.seleniumhq.org) for API client testing over Http, which requires a WebDriver to be downloaded. 

The default driver is set to Chrome. Be sure that you have both [Chrome](https://www.google.se/chrome/browser/desktop/index.html) and a [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) installed. 

Next, you have to set the location path of the WebDriver in the environment variable `SELENIUM_WEBDRIVER_PATH`.

Run following command to trigger the tests in directory `src/integration-test/java`:

```sh
mvn integration-test
```

## Eclipse IDE Support

Adds Eclipse Dynamic Web support to the project:

```sh
mvn eclipse:eclipse
```

## Build and run with Docker

First build and package the project with maven:

```sh
docker run -it --rm -v "$PWD":/app -w /app maven:3.3-jdk-8 mvn clean package
```

Build your image:

```sh
docker build --rm=false -t aws-java-app .
```

Run the recently built image:

```sh
docker run --rm -p 8080:8080 aws-java-app
```

Enter following URL in your browser:

```sh
http://localhost:8080/aws-java-app
```


