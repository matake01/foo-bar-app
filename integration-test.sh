#! /bin/bash

execute_tests () {
  \mvn verify
}

fetch_chromedriver () {
  echo "Downloading ChromeDriver..."
  wget -nv https://chromedriver.storage.googleapis.com/2.31/chromedriver_linux64.zip

  echo "Extracting file..."
  unzip chromedriver_linux64.zip

  WEBDRIVER_NAME="chromedriver"
  echo "$WEBDRIVER_NAME fetched successfully!"
}

handle_selenium_webdrivers () {
  fetch_chromedriver

  CURRENT_PATH=$(pwd)
  echo "PWD => $CURRENT_PATH"

  WEBDRIVER_PATH="$CURRENT_PATH/$WEBDRIVER_NAME"

  chmod +x $WEBDRIVER_PATH

  SELENIUM_ENV='SELENIUM_WEBDRIVER_PATH'
  unset $SELENIUM_ENV

  export $SELENIUM_ENV="$WEBDRIVER_PATH"
  echo "ENV => $SELENIUM_ENV=$WEBDRIVER_PATH"
}

handle_selenium_webdrivers
# execute_tests
