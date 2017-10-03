# DevOps Java Application Template

This DevOps web app boilerplate template is created regarding to the Maven Webapp archetype with Spring REST & Data JPA support added. 

The project is also following the standards for Continuous Integration & Deployment by using defined pipelines (`cricle.yml`) at the cloud service [CircleCI](http://circleci.com).

The deployment stage is covered by [Docker Hub](hub.docker.com) and distribution at AWS using [Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/).

Following command will build and package the project:

```sh
mvn clean install
```

## Prerequisites

- JDK 1.8
- Maven 3.X
- Selenium WebDriver

## Techniques used

- [Spring REST Service](https://spring.io/guides/gs/rest-service/)
- [Spring Data JPA](https://projects.spring.io/spring-data-jpa/)
- [Docker](http://www.docker.com)
- [CircleCI](http://circleci.com)
- [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/)
- [SeleniumHQ](http://seleniumhq.org)
- [HSQLDB](http://hsqldb.org/)

## Configuration

### Setup environment variables

Before you're able to build the project, some environment variables need to be setup with the correct values.

#### Persistence Context

* **DB_DRIVER** - The connector driver (E.g. com.mysql.cj.jdbc.Driver)
* **DB_URL** - The full url to your DB (E.g. jdbc:mysql://localhost:3306/aws_java_app)
* **DB_USERNAME** - Your DB Username
* **DB_PASSWORD** - Your DB Password
* **HIBERNATE_DIALECT** - The hibernate dialect type(E.g. org.hibernate.dialect.MySQLDialect)
* **HIBERNATE_HBM2DDL_AUTO** - (Optional) In development/test mode this may be set to 'create-drop' 

#### Selenium

This boilerplate project uses [Selenium](http://www.seleniumhq.org) for API client testing over Http, which requires a WebDriver to be downloaded. 

The default driver is set to Chrome. For this case; be sure that both [Chrome](https://www.google.se/chrome/browser/desktop/index.html) and a [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) is installed. 

* **SELENIUM_WEBDRIVER_PATH** - The full path to your local WebDriver 

#### Logging

To be able to store your logging entries, following variable need to be set:

* **LOG_DIR** - The path to the directory where you want your logging files to be persisted. 

## Testing

Supports both Unit and Integration Tests using Maven Surefire and Failsafe plugins.

Verify and run all tests with your environment database:

```sh
mvn verify
```

Verify and run all tests with the pre-defined in-memory database profile:

```sh
mvn verify -Ptests
```

#### Run Unit Tests

Runs tests located under `src/test/java` by:

```sh
mvn test
```

#### Run Integration Tests

Run tests located under `src/integration-test/java` by:

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
docker build --rm=false -t app .
```

Run the recently built image:

```sh
docker run --rm -p 8080:8080 app
```

Enter following URL in your browser:

```sh
http://localhost:8080/app
```


