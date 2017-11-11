# DevOps Java Application Template

A boilerplate Java project focused on deliver basic DevOps flows such Continuous Integration, Continuous Delivery, containerizing with Docker and Cloud Computing over AWS Elastic Beanstalk.

# Getting Started

All Maven executions will use the pre-defined `test` profile (see section *Maven Profiles* below) and HSQLDB in-memory database by default.

Make a fresh install:

```sh
mvn clean install
```

Build the final war and exclude any integration tests to speed up the process:

```sh
mvn package -DskipITs
```

## Prerequisites

- JDK 1.8
- Maven 3.X
- [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads)

## Techniques used

- [Spring REST Service](https://spring.io/guides/gs/rest-service/)
- [Spring Data JPA](https://projects.spring.io/spring-data-jpa/)
- [Docker](http://www.docker.com)
- [CricleCI](https://circleci.com/)
- [AWS ElasticBeanstalk](https://aws.amazon.com/elasticbeanstalk/)
- [SeleniumHQ](http://seleniumhq.org)
- [HSQLDB](http://hsqldb.org/)

## System Properties

Following attributes has to be set either in your global environment or JVM:

* **app.domain** - Used for integration tests. Defines the HTTP root endpoint where the application runs (E.g. http://example.com/app) 
* **spring.profiles.active** - Set to either *'development'* or *'production'*. See section *Spring Profiles* for more details.
* **spring.datasource.jdbc.driver** - The JDBC Driver
* **spring.datasource.jdbc.url** - The DB URL
* **spring.datasource.jdbc.username** - The DB username
* **spring.datasource.jdbc.password** - The DB password
* **spring.jpa.hibernate.dialect** - The dialect type of the ORM. E.g. *org.hibernate.dialect.MySQLDialect*
* **spring.jpa.hibernate.hbm2ddl.auto** - Defines how the database schema should be populated: *'create'*, *'create-drop'*, *'validate'* or *'none'*.
* **webdriver.chrome.driver** - The path to the [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) in your global environment. 
* **log.dir** - The path to store log files

## Spring Profiles

During application runtime either one or more profiles may be active within the context. The behaviour may vary depending on the profile configuration. 

The application is pre-defined with two default profiles: *development* and *production*.

### Development

Activate the development profile:

```sh
spring.profiles.active="development"
```

### Production

Activate the production profile:

```sh
spring.profiles.active="production"
```

The production profile differentiates from the development behaviour in the following way:

```sh
spring.jpa.hibernate.hbm2ddl.auto="none"
```

## Maven Profiles

### Test

A pre-defined profile is configured in the `pom.xml`. The profile is mainly for usage within Autonuomous Testing/Continuous Integration pipelines.

```sh
app.domain="http://localhost:9090/app"
spring.profiles.active="dev"
spring.datasource.jdbc.driver="org.hsqldb.jdbcDriver"
spring.datasource.jdbc.url="jdbc:hsqldb:hsql://localhost/xdb"
spring.datasource.jdbc.username="sa"
spring.datasource.jdbc.password=""
spring.jpa.hibernate.dialect="org.hibernate.dialect.HSQLDialect"
spring.jpa.hibernate.hbm2ddl.auto="create-drop"
webdriver.chrome.driver="${env.webdriver_chrome_driver}"
log.dir="${project.basedir}/log"
```


## Testing

## Verify your commit

Before any commits are performed into the global VCS, be sure that the last changes are verified. 

Following command will execute all available unit and integration tests:

```sh
mvn verify
```

**Notice:** This is a mandatory step. Otherwise, your changes may fail in the autonomous pipelines.  


#### Unit Tests

Tests the behaviours of single components according to the [SOLID](https://en.wikipedia.org/wiki/SOLID_(object-oriented_design)) pattern.

Directory location: `src/test/java`

```sh
mvn test
```

#### Integration Tests

The integration tests covers more complex behaviours and dependencies between components. E.g. BDD, API and database tests.   

Direcotry location: `src/integration-test/java`

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


