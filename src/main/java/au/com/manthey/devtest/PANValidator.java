package au.com.manthey.devtest;

public class PANValidator {
    public static String validate(String pan) {
        String cardType = "VisaMaster";
        boolean validity = false;
        return String.format("%s: %s (%s)", cardType, pan, validity?"valid":"invalid");
    }
}
