FROM tomcat:8

MAINTAINER Mathias Åkerberg <maker@payer.se>

ADD target/*.war /usr/local/tomcat/webapps/

# Build with following command:
# docker run -it --rm -v "$PWD":/app -w /app maven:3.3-jdk-8 mvn clean package
# docker build --rm=false -t aws-java-app .
# docker run --rm -p 8080:8080 aws-java-app