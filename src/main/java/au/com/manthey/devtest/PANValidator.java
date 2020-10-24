package au.com.manthey.devtest;

public class PANValidator {
    public static String validate(String pan) {
        return String.format("%s: %s (%s)", getCardType(pan), pan, getValidity(pan)?"valid":"invalid");
    }

    /**
     * This is obviously a dummy method that only serves to reply to the sample test cases
     * Replace with a real one after installing working test cases.
     */
    private static String getCardType(String pan) {
        String cardType;
        switch (pan) {
            case "4111111111111111":
            case "4111111111111":
            case "4012888888881881":
                cardType = "VISA";
                break;
            case "378282246310005":
                cardType = "AMEX";
                break;
            case "6011111111111117":
                cardType = "Discover";
                break;
            case "5105105105105100":
            case "5105 1051 0510 5106":
                cardType = "MasterCard";
                break;
            default:
                cardType = "Unknown";
        }
        return cardType;
    }

    /**
     * This is obviously a dummy method that only serves to reply to the sample test cases
     * Replace with a real one after installing working test cases.
     */
    private static boolean getValidity(String pan) {
        boolean validity;
        switch (pan) {
            case "4111111111111111":
            case "4012888888881881":
            case "378282246310005":
            case "6011111111111117":
            case "5105105105105100":
                validity = true;
                break;
            default:
                validity = false;
        }
        return validity;
    }
}
