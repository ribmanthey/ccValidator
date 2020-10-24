package au.com.manthey.devtest;
import static au.com.manthey.devtest.PANValidator.validate;
import static spark.Spark.get;
import static spark.Spark.port;

public class RestApp {
    public static void main(String[] args) {
        port(8080);
        get("/", (req,res) -> "Usage: <a href='http://localhost:8080/1234567890123456'>http://localhost:8080/1234567890123456</a>");
        get("/:cc", (req,res)-> validate(req.params(":cc")));
    }
}