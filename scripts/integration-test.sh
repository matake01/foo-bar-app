#! /bin/bash

# Generic
CIRCLE_SHA1=$1
CURRENT_PATH=$(pwd)
HOST_IP_ADDRESS='localhost'

# Chrome Driver
CHROME_DRIVER_URL='https://chromedriver.storage.googleapis.com'
CHROME_DRIVER_VERSION='2.31'
CHROME_DRIVER_FILE_NAME='chromedriver_linux64.zip'

# Chrome Stable
CHROME_STABLE_FILE_NAME='google-chrome-stable_current_amd64.deb'
CHROME_STABLE_URL="https://dl.google.com/linux/direct/$CHROME_STABLE_FILE_NAME"

# Docker
DOCKER_REGISTRY=$2
DOCKER_IMAGE=$3
DOCKER_APP_PORT='8081'
DOCKER_APP_CONTAINER_NAME='app'
DOCKER_MYSQL_PORT='33306'
DOCKER_MYSQL_CONTAINER_NAME='app-mysql'

# App Envs
SPRING_PROFILES_ACTIVE='dev'
SPRING_DATASOURCE_JDBC_DRIVER='com.mysql.cj.jdbc.Driver'
SPRING_DATASOURCE_JDBC_URL='jdbc:mysql://app_mysql:33306/appdb?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC'
SPRING_DATASOURCE_JDBC_USERNAME='appuser'
SPRING_DATASOURCE_JDBC_PASSWORD='appuser'
SPRING_JPA_HIBERNATE_DIALECT='org.hibernate.dialect.MySQLDialect'
SPRING_JPA_HIBERNATE_HBM2DDL_AUTO='create-drop'
LOG_DIR="logs"

# MySQL Generics
MYSQL_SERVER_VERSION='5.6'

# MySQL Envs
MYSQL_ROOT_PASSWORD='secretpassword'
MYSQL_DATABASE='appdb'
MYSQL_USER='appuser'
MYSQL_PASSWORD='apppassword'

# Selenium
SELENIUM_WEBDRIVER_ENV='TEST_WEBDRIVER_CHROME_DRIVER'
SELENIUM_WEBDRIVER_FILE_NAME='chromedriver'
SELENIUM_WEBDRIVER_LOCATION="$CURRENT_PATH/$SELENIUM_WEBDRIVER_FILE_NAME"

init () {
    export LOG_DIR=$LOG_DIR
}

test_docker_access () {
  echo "Test Docker Access."

  install_mysql
  run_application
  test_http_app_access
}

test_software () {
  echo "Test Software."

  \mvn verify
}

setup_selenium_environment () {
  echo "Setup Selenium Environment."

  install_chrome
  install_chromedriver
  chmod +x $SELENIUM_WEBDRIVER_PATH
  set_selenium_env
}

install_chrome () {
  echo "Install Chrome."

  wget -N $CHROME_STABLE_URL -P ~/
  sudo dpkg -i --force-depends ~/$CHROME_STABLE_FILE_NAME
  sudo apt-get -f install -y
  sudo dpkg -i --force-depends ~/$CHROME_STABLE_FILE_NAME
}

install_mysql () {
  echo "Install MySQL."

  \docker run --name $DOCKER_MYSQL_CONTAINER_NAME \
  -p $DOCKER_MYSQL_PORT:3306 \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
  -e MYSQL_DATABASE=$MYSQL_DATABASE \
  -e MYSQL_USER=$MYSQL_USER \
  -e MYSQL_PASSWORD=$MYSQL_PASSWORD \
  -d mysql/mysql-server:$MYSQL_SERVER_VERSION

  \sleep 3
}

install_chromedriver () {
  echo "Install ChromeDriver."

  wget -nv $CHROME_DRIVER_URL/$CHROME_DRIVER_VERSION/$CHROME_DRIVER_FILE_NAME
  unzip $CHROME_DRIVER_FILE_NAME
}

run_application () {
    echo "Start App."

  \docker run --name $DOCKER_APP_CONTAINER_NAME \
  -p $DOCKER_APP_PORT:8080 \
  --link $DOCKER_MYSQL_CONTAINER_NAME:mysql \
  -e JAVA_OPTS=" \
    -Dlog.dir=$LOG_DIR \
    -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE \
    -Dspring.datasource.jdbc.driver=$SPRING_DATASOURCE_JDBC_DRIVER \
    -Dspring.datasource.jdbc.username=$SPRING_DATASOURCE_JDBC_USERNAME \
    -Dspring.datasource.jdbc.password=$SPRING_DATASOURCE_JDBC_PASSWORD \
    -Dspring.jpa.hibernate.dialect=$SPRING_JPA_HIBERNATE_DIALECT \
    -Dspring.jpa.hibernate.hbm2ddl.auto=$SPRING_JPA_HIBERNATE_HBM2DDL_AUTO \
    -Dspring.datasource.jdbc.url=\"$SPRING_DATASOURCE_JDBC_URL\" \
    " \
  -d $DOCKER_REPOSITORY/$DOCKER_IMAGE:$TAG

  \sleep 3
}

set_selenium_env () {
  echo "Setup Selenium Environment variables..."

  unset $SELENIUM_WEBDRIVER_ENV
  export $SELENIUM_WEBDRIVER_ENV="$SELENIUM_WEBDRIVER_LOCATION"
  echo "ENV => $SELENIUM_WEBDRIVER_ENV=$SELENIUM_WEBDRIVER_LOCATION"
}

test_http_app_access () {
  echo "Tests HTTP access of Docker image."

  URL="http://$HOST_IP_ADDRESS:$DOCKER_APP_PORT/app"
  echo " URL => $URL"

  \curl -v -L --retry 5 --retry-delay 5 -v $URL
}

init
setup_selenium_environment
test_software
test_docker_access
