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
DOCKER_CONTAINER_NAME_MYSQL='app_mysql'
DOCKER_CONTAINER_NAME_APP='app'

# App Envs
APP_NAME=$DOCKER_CONTAINER_NAME_APP
APP_PORT='8080'
DB_DRIVER='com.mysql.cj.jdbc.Driver'
DB_URL='jdbc:mysql://app_mysql:33306/appdb?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC'
DB_USERNAME='appuser'
DB_PASSWORD='appuser'
HIBERNATE_DIALECT='org.hibernate.dialect.MySQLDialect'
HIBERNATE_HBM2DDL_AUTO='create-drop'

# Database
MYSQL_PORT='33306'
MYSQL_SERVER_VERSION='5.6'
MYSQL_ROOT_PASSWORD='secretpassword'
MYSQL_DATABASE='appdb'
MYSQL_USER='appuser'
MYSQL_PASSWORD='apppassword'

# Selenium
SELENIUM_WEBDRIVER_ENV='SELENIUM_WEBDRIVER_PATH'
SELENIUM_WEBDRIVER_FILE_NAME='chromedriver'
SELENIUM_WEBDRIVER_LOCATION="$CURRENT_PATH/$SELENIUM_WEBDRIVER_FILE_NAME"

test_docker_access () {
  echo "Test Docker Access..."

  install_mysql
  run_application
  test_http_app_access
}

test_software () {
  echo "Test software..."

  \mvn verify
}

setup_selenium_environment () {
  echo "Setup Selenium..."

  install_chrome
  install_chromedriver
  chmod +x $SELENIUM_WEBDRIVER_PATH
  set_selenium_env
}

install_chrome () {
  echo "Install Chrome Stable..."

  wget -N $CHROME_STABLE_URL -P ~/
  sudo dpkg -i --force-depends ~/$CHROME_STABLE_FILE_NAME
  sudo apt-get -f install -y
  sudo dpkg -i --force-depends ~/$CHROME_STABLE_FILE_NAME
}

install_mysql () {
  echo "Install MySQL..."

  \docker run --name $DOCKER_CONTAINER_NAME_MYSQL \
  -p $MYSQL_PORT:3306 \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
  -e MYSQL_DATABASE=$MYSQL_DATABASE \
  -e MYSQL_USER=$MYSQL_USER \
  -e MYSQL_PASSWORD=$MYSQL_PASSWORD \
  -d mysql/mysql-server:$MYSQL_SERVER_VERSION
}

install_chromedriver () {
  echo "Install ChromeDriver..."

  wget -nv $CHROME_DRIVER_URL/$CHROME_DRIVER_VERSION/$CHROME_DRIVER_FILE_NAME
  unzip $CHROME_DRIVER_FILE_NAME
}

run_application () {
  echo "Runs application..."

  docker run --name $DOCKER_CONTAINER_NAME_APP \
  -p $APP_PORT:8080 \
  --link $DOCKER_CONTAINER_NAME_MYSQL:mysql \
  -e DB_DRIVER=$DB_DRIVER \
  -e DB_URL=$DB_URL \
  -e DB_USERNAME=$DB_USERNAME \
  -e DB_PASSWORD=$DB_PASSWORD \
  -e HIBERNATE_DIALECT=$HIBERNATE_DIALECT \
  -e HIBERNATE_HBM2DDL_AUTO=$HIBERNATE_HBM2DDL_AUTO \
  -d $DOCKER_REGISTRY/$DOCKER_IMAGE
}

set_selenium_env () {
  echo "Setup Selenium Environment variables..."

  unset $SELENIUM_WEBDRIVER_ENV
  export $SELENIUM_WEBDRIVER_ENV="$SELENIUM_WEBDRIVER_LOCATION"
  echo "ENV => $SELENIUM_WEBDRIVER_ENV=$SELENIUM_WEBDRIVER_LOCATION"
}

test_http_app_access () {
  echo "Tests HTTP access of Docker image..."

  URL="http://$HOST_IP_ADDRESS:$APP_PORT/$APP_NAME"
  echo " URL => $ACCESS_URL"

  \curl -v -L --retry 5 --retry-delay 5 -v $ACCESS_URL
}

setup_selenium_environment
test_software
test_docker_access
