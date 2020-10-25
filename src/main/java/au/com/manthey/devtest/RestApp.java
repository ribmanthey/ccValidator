package au.com.manthey.devtest;
import static au.com.manthey.devtest.PANValidator.validate;
import static spark.Spark.get;
import static spark.Spark.port;

public class RestApp {

    public static final String SERVER = "localhost";
    public static final int PORT = 8080;
    public static final String HOST = SERVER + ":" + PORT;

    public static final String USAGE_HINT = String.format("Usage: <a href='http://%s/1234567890123456'>http://%s/1234567890123456</a>", HOST, HOST);

    public static void main(String[] args) {
        port(PORT);
        get("/", (req,res) -> USAGE_HINT);
        get("/:cc", (req,res)-> validate(req.params(":cc")));
    }
}