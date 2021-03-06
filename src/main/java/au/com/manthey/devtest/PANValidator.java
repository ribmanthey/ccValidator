package au.com.manthey.devtest;

public class PANValidator {

    public static String validate(String pan) {
        Object[] cardTypeAndValidity = getCardTypeAndValidity(pan);
        String cardType = (String)cardTypeAndValidity[0];
        boolean validity = (boolean)cardTypeAndValidity[1];
        return String.format("%s: %s (%s)", cardType, pan, validity?"valid":"invalid");
    }

    /**
     * Card Type       Begins With       Number Length
     * AMEX            34 or 37          15
     * Discover        6011              16
     * MasterCard      51-55             16
     * Visa            4                 13 or 16
     */
    private static Object[] getCardTypeAndValidity(String pan) {
        String cardType = "Unknown";
        boolean validity = true;
        long panNum = 0;
        int panLen = 0;

        try {
            // Strip spaces, so that the card type can be identified even if it's then invalid
            String squashedPan = pan.replaceAll(" ","");
            if (squashedPan.length() != pan.length()) validity = false;
            panNum = Long.parseLong(squashedPan);
            panLen = squashedPan.length();

            int digit1 = (int) (panNum/Math.pow(10,panLen-1));
            if (digit1 == 4) {
                cardType = "VISA";
                if (panLen != 13 && panLen != 16)
                    validity = false;
            } else {
                int digits12 = (int) (panNum/Math.pow(10,panLen-2));

                if (digits12 == 34 || digits12 == 37) {
                    cardType = "AMEX";
                    if (panLen != 15)
                        validity = false;
                } else {
                    if (digits12 >= 51 && digits12 <= 55) {
                        cardType = "MasterCard";
                        if (panLen != 16)
                            validity = false;
                    } else {
                        int digits1234 = (int) (panNum/Math.pow(10,panLen-4));

                        if (digits1234 == 6011) {
                            cardType = "Discover";
                            if (panLen != 16)
                                validity = false;
                        }
                    }
                }
            }

        } catch (NumberFormatException e) {
            validity = false;
            // fall through to "Unknown", invalid
        }
        if ("Unknown".equals(cardType)) validity = false;
        validity = validity && luhnTest(panNum, panLen);

        return new Object[] {cardType, validity};
    }

    private static boolean luhnTest(long panNum, int panLen) {
        int luhnSum = 0;
        for (int i = 0; i < panLen; i++) {
            int tailDigit = (int) (panNum%10);
            if (i%2 == 1) {
                // double every second digit from tail
                tailDigit = tailDigit*2;
                if (tailDigit>9){
                    // split the doubled digit if >10 and sum them as one
                    tailDigit = (tailDigit%10 + 1);
                }
            }
            // sum in the plain or doubled split digit
            luhnSum += tailDigit;
            // move on to the next tail digit
            panNum = panNum/10;
        }
        // find if it's a multiple of 10
        return luhnSum%10 == 0;
    }
}
