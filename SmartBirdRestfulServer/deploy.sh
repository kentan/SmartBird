#!/bin/sh
pwd
mvn clean install

rm -rf /Users/kentan/Documents/apache-tomcat-7.0.52//webapps/SmartBirdRestfulServer

mv ./target/SmartBirdRestfulServer.war /Users/kentan/Documents/apache-tomcat-7.0.52//webapps/.


/Users/kentan/Documents/apache-tomcat-7.0.52/bin/shutdown.sh

/Users/kentan/Documents/apache-tomcat-7.0.52/bin/startup.sh
