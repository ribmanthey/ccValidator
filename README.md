# Spike of a REST API

## Principles
* Keep it Short and Simple
    * don't over-work it, less is more
* Spring is not required, but java, go, or node.js is.
* break up problem into portions to solve, aka sprint cards
* pick a tech stack that is both interesting to the audience and relevant to my skill history.
    * node and Go, not in my immediate knowledge, let's try the java variation first, maybe return to these if I kill the java one.
    * Spring - bit heavy, also not super in favour to the audience possibly
    * Grails - Groovy not part of the audience's stack
    * Ultralight REST servers, Micronaut, Spark, etc - promising for a very lightweight microservice, in favour in the tech circles, let's try one.  Might be able to run one on GraalVM if picked neatly, but that might not integrate with (Gradle?) automated testing.  Risk? Accepted.

## Sprint cards
* MVP1
    * a working REST API server, one endpoint, dummy backend that supplies mapped data results
        * DONE
    * automated test that exercises the endpoint
        * DONE
    * set of unit tests that exercise the range of valid tests given
        * DONE
        * note: Groovy used for the simplicity of testing over sa map of strings
    * set of integration tests that exercise the range of valid tests given, via the endpoint
        * DONE
    * replace dummy back end with active algorithm, tests confirm good-to-go
        * DONE
* MVP2
    * oblige the provision of a security token to access, eg: JWT
    * add security testing
* MVP3
    * add performance testing
    * if viable, compile jar into GraalVM native and demonstrate

## Runtime Instructions
* checkout the code from github
* from your shell, to run the automated build and unit and integration tests:
    * ./gradlew clean build
* to run the server and manually test PANs:
    * ./gradlew run
    * from a browser connect to: http://localhost:8080
    * it will give you usage instructions to test PANs from the url: eg: http://localhost:8080/1234567890123456
    * ^c to stop the server
* it also all runs nicely in IntelliJ :)

## Room to Improve
* Bring server location, port in from properties files, eg: application.yml
* Include a clover test to find uncovered code
* add a parameterised version of the rest-assured test to test the given sample variations
* update libraries to later versions
* some code in the tests could be shared, especially the test data blob
* VISA and Visa are both stated in the spec.  It would be good to be clear which.
* The Luhn sum test deserves some corner case testing but the given sample tests pass.  I left in some println's as the Luhn algorithm might be a bit unclear as to what I was doing.
* the card type and validity tests were combined into a single function with a multiple return (like Python) as I was reluctant to set field values in the class, aiming to have a pure function that can be static.  This really deserves to be redone in java 8 lambda style too.
* relying on gradle to run the server should be improved by actaully building a deployable jar or runnable graalvm executable