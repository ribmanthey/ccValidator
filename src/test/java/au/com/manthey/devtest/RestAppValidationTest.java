package au.com.manthey.devtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class RestAppValidationTest extends RestAppShared {

    @Parameterized.Parameters(name = "{index}: call to {0}, response: {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"4111111111111111",    "VISA: 4111111111111111 (valid)"},
            {"4111111111111",       "VISA: 4111111111111 (invalid)"},
            {"4012888888881881",    "VISA: 4012888888881881 (valid)"},
            {"378282246310005",     "AMEX: 378282246310005 (valid)"},
            {"6011111111111117",    "Discover: 6011111111111117 (valid)"},
            {"5105105105105100",    "MasterCard: 5105105105105100 (valid)"},
            {"5105 1051 0510 5106", "MasterCard: 5105 1051 0510 5106 (invalid)"},
            {"9111111111111111",    "Unknown: 9111111111111111 (invalid)"}
        });
    }

    private final String req;
    private final String res;

    public RestAppValidationTest(String req, String res) {
        this.req = req;
        this.res = res;
    }

    @Test
    public void dataDrivenEndpointTest() {
        given().
        when().
            get(String.format("http://%s/%s", RestApp.HOST, req)).
        then().
            assertThat().
            statusCode(200).
            body(equalTo(res));
    }
}
