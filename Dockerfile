FROM tomcat:8

MAINTAINER Mathias Åkerberg <zegoffinator@gmail.com>

ADD target/*.war /usr/local/tomcat/webapps/