FROM tomcat:8-jre8

MAINTAINER Mathias Åkerberg <zegoffinator@gmail.com>

ADD target/*.war /usr/local/tomcat/webapps/