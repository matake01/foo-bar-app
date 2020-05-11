# foo-bar-app

## Prerequisites

- JDK 1.8
- Maven 3.X
- [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads)

## Techniques used

- [Spring](https://spring.io/)
- [Selenium](http://seleniumhq.org/)
- [Docker](http://www.docker.com)
- [CricleCI](https://circleci.com/)
- [AWS ElasticBeanstalk](https://aws.amazon.com/elasticbeanstalk/)

## Build and run

Clean and build:

```sh
mvn clean install
```

Skip integration tests:

```sh
mvn package -DskipITs
```

Skip all tests:

```sh
mvn package -DskipTests
```

Run tests:

```sh
mvn verify
```

## System Properties

### Base
* **spring.profiles.active** - Set to either *'development'* or *'production'*. See section *Spring Profiles* for more details.
* **log.dir** - The path to store log files

### Persistence

* **spring.datasource.jdbc.driver** - The JDBC Driver
* **spring.datasource.jdbc.url** - The DB URL
* **spring.datasource.jdbc.username** - The DB username
* **spring.datasource.jdbc.password** - The DB password
* **spring.jpa.hibernate.dialect** - The dialect type of the ORM. E.g. *org.hibernate.dialect.MySQLDialect*
* **spring.jpa.hibernate.hbm2ddl.auto** - Defines how the database schema should be populated: *'create'*, *'create-drop'*, *'validate'* or *'none'*.

### Tests
* **test.app.domain** - Used for integration tests. Defines the HTTP root endpoint where the application runs (E.g. http://example.com/app)
* **test.user.agentid** - The AgentId of the superuser context during integration testing
* **test.webdriver.chrome.driver** - Used by Selenium. Should be the full path to the [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) in your local environment.

## Spring Profiles

### Development

```sh
spring.profiles.active="dev"
```

### Production

```sh
spring.profiles.active="production"
```

## Maven Profiles

### Test

A pre-defined profile is configured in the `pom.xml`. The profile is mainly for usage within Autonuomous Testing/Continuous Integration pipelines.

```sh
spring.profiles.active="dev"
spring.datasource.jdbc.driver="org.hsqldb.jdbcDriver"
spring.datasource.jdbc.url="jdbc:hsqldb:hsql://localhost/xdb"
spring.datasource.jdbc.username="sa"
spring.datasource.jdbc.password=""
spring.jpa.hibernate.dialect="org.hibernate.dialect.HSQLDialect"
spring.jpa.hibernate.hbm2ddl.auto="create-drop"
test.app.domain="http://localhost:9090/app"
test.user.agentid="${env.TEST_USER_AGENTID}"
test.webdriver.chrome.driver="${env.TEST_WEBDRIVER_CHROME_DRIVER}"
log.dir="${project.basedir}/log"
```

## Eclipse IDE Support

Adds Eclipse Dynamic Web support to the project:

```sh
mvn eclipse:eclipse
```

## Build and run with Docker

Build Java binary:

```sh
docker run -it --rm -v "$PWD":/app -w /app maven:3.3-jdk-8 mvn clean package
```

Build your image:

```sh
docker build --rm=false -t app .
```

Run built image:

```sh
docker run --rm -p 8080:8080 app
```

Now available under:

```sh
http://localhost:8080/app
```
