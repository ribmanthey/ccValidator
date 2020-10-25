package au.com.manthey.devtest

import org.junit.Test

class PANValidatorTest {
    def sample = [
            "4111111111111111":    "VISA: 4111111111111111 (valid)",
            "4111111111111":       "VISA: 4111111111111 (invalid)",
            "4012888888881881":    "VISA: 4012888888881881 (valid)",
            "378282246310005":     "AMEX: 378282246310005 (valid)",
            "6011111111111117":    "Discover: 6011111111111117 (valid)",
            "5105105105105100":    "MasterCard: 5105105105105100 (valid)",
            "5105 1051 0510 5106": "MasterCard: 5105 1051 0510 5106 (invalid)",
            "9111111111111111":    "Unknown: 9111111111111111 (invalid)"
    ]

    @Test
    void validateTest() {
        sample.each {
            assert PANValidator.validate(it.key) == it.value
        }
    }
}
