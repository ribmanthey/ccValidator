package au.com.manthey.devtest;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAppTest {

    @BeforeClass
    static public void before() throws InterruptedException {
        String [] args = {};
        RestApp.main(args);
        Thread.sleep(2000);
        RestAssured.registerParser("text/plain", Parser.TEXT);
    }

    @Test
    public void endpointUsageTest() {
        given().
                when().
                get(String.format("http://%s/",RestApp.HOST)).
                then().
                assertThat().
                statusCode(200).
                body(equalTo(RestApp.USAGE_HINT));
    }

    @Test
    public void endpointValidatorSinglePositiveTest() {
        given().
                when().
                get(String.format("http://%s/4111111111111111", RestApp.HOST)).
                then().
                assertThat().
                statusCode(200).
                body(equalTo("VISA: 4111111111111111 (valid)"));
    }

    @Test
    public void endpointValidatorSingleNegativeTest() {
        given().
                when().
                get(String.format("http://%s/666", RestApp.HOST)).
                then().
                assertThat().
                statusCode(200).
                body(equalTo("Unknown: 666 (invalid)"));
    }
}
