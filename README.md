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
    * replace dummy back end with active algorithm, tests confirm good-to-go
* MVP2
    * oblige the provision of a security token to access, eg: JWT
    * add security testing
* MVP3
    * add performance testing
    * if viable, compile jar into GraalVM native and demonstrate

## Room to Improve
* Bring server location, port in from properties files, eg: application.yml
* Include a clover test to find uncovered code
* add a parameterised version of the rest-assured test to test the given sample variations