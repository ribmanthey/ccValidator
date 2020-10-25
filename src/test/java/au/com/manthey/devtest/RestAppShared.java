package au.com.manthey.devtest;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class RestAppShared {

    @BeforeClass
    public static void startServer() throws InterruptedException {
        String [] args = {};
        RestApp.main(args);
        Thread.sleep(2000); // I found the Jetty server started in <1.7 secs
        RestAssured.registerParser("text/plain", Parser.TEXT);
    }

    @AfterClass
    public static void stopServer() {
        RestApp.stopServer();
    }

}
