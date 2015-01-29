Simple Rest Service created by assembling embedded Jetty, latest version of Jersey and JUnit Testcase. Working using Gradle and Intellij.

Simply start the Jetty Server in Intellij ->

Main function of rest.Webserver class. It will start the jetty webserver and start serving the REST endpoint.

simple test ->
http://localhost:8081/jsonServices/test

json text ->
http://localhost:8081/jsonServices/print/yourname

You can run APITEST Junit testcase to test the rest service.