package au.com.manthey.devtest

import org.junit.Test

class PANValidatorTest {
    def sample = [
            // Supplied test samples
            "4408041234567893":    "VISA: 4408041234567893 (valid)", // Luhn pass
            "4417123456789112":    "VISA: 4417123456789112 (invalid)", // Luhn fail
            "4111111111111111":    "VISA: 4111111111111111 (valid)",
            "4111111111111":       "VISA: 4111111111111 (invalid)",
            "4012888888881881":    "VISA: 4012888888881881 (valid)",
            "378282246310005":     "AMEX: 378282246310005 (valid)",
            "6011111111111117":    "Discover: 6011111111111117 (valid)",
            "5105105105105100":    "MasterCard: 5105105105105100 (valid)",
            "5105 1051 0510 5106": "MasterCard: 5105 1051 0510 5106 (invalid)",
            "9111111111111111":    "Unknown: 9111111111111111 (invalid)",

            // AMEX range
            "3":                   "Unknown: 3 (invalid)",
            "33":                  "Unknown: 33 (invalid)",
            "34":                  "AMEX: 34 (invalid)",
            "35":                  "Unknown: 35 (invalid)",
            "36":                  "Unknown: 36 (invalid)",
            "37":                  "AMEX: 37 (invalid)",
            "38":                  "Unknown: 38 (invalid)",
            "3499999999999999":    "AMEX: 3499999999999999 (invalid)",
            "3799999999999999":    "AMEX: 3799999999999999 (invalid)",

            // Visa range
            "4":                   "VISA: 4 (invalid)",
            "44":                  "VISA: 44 (invalid)",
            "444":                 "VISA: 444 (invalid)",
            "4999999999999999":    "VISA: 4999999999999999 (invalid)",

            // MasterCard range
            "50":                  "Unknown: 50 (invalid)",
            "51":                  "MasterCard: 51 (invalid)",
            "52":                  "MasterCard: 52 (invalid)",
            "53":                  "MasterCard: 53 (invalid)",
            "54":                  "MasterCard: 54 (invalid)",
            "55":                  "MasterCard: 55 (invalid)",
            "56":                  "Unknown: 56 (invalid)",
            "5199999999999999":    "MasterCard: 5199999999999999 (invalid)",

            // Discover range
            "6010":                "Unknown: 6010 (invalid)",
            "6011":                "Discover: 6011 (invalid)",
            "6012":                "Unknown: 6012 (invalid)",
            "6011999999999999":    "Discover: 6011999999999999 (invalid)",

            // Luhn fails (passing values incremented by 1)
            "4111111111111112":    "VISA: 4111111111111112 (invalid)",
            "378282246310006":     "AMEX: 378282246310006 (invalid)",
            "6011111111111118":    "Discover: 6011111111111118 (invalid)",
            "5105105105105101":    "MasterCard: 5105105105105101 (invalid)"
    ]

    @Test
    void validateTest() {
        sample.each {
            assert PANValidator.validate(it.key) == it.value
        }
    }
}
