import org.junit.platform.commons.logging.Logger
import org.junit.platform.commons.logging.LoggerFactory

/**
 *  Calculator
 *
 */
class Calculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class)

    static int calculate(String input) {

        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException()
        }

        int currentNumber = 0
        char operator = '+'

        def stripped = input.replace(' ', '')
        def makeArray = stripped.&toCharArray
        def inputArray = makeArray()

        Stack<BigInteger> storageNumbers = new Stack<>()
        for (int i = 0; i < inputArray.length; i++) {
            if (Character.isDigit(inputArray[i])) {
                currentNumber = currentNumber * 10 + (inputArray[i] - 48)
            }

            if (!Character.isDigit(inputArray[i]) || i == inputArray.length - 1) {
                switch (operator) {
                    case '+': storageNumbers.push(currentNumber as BigInteger)
                        break
                    case '-': storageNumbers.push(-currentNumber as BigInteger)
                        break
                    case '*': storageNumbers.push(storageNumbers.pop() * currentNumber)
                        break
                    case '/': storageNumbers.push(storageNumbers.pop() / currentNumber as BigInteger)
                        break
                    default:
                        throw new IllegalArgumentException("Invalid number ")
                }
                operator = inputArray[i]
                currentNumber = 0
            }
        }
        return addingNumbersInStorage(storageNumbers)
    }

    private static BigInteger addingNumbersInStorage(Stack<BigInteger> storageNumbers) {
        LOGGER.info(() -> "method adding number was called")
        def printingResult = { param -> println("the result is : ${param}") }
        BigInteger result = 0
        while (!storageNumbers.isEmpty()) {
            def pop = storageNumbers.pop()
            result += pop
            LOGGER.info(() -> "the current sum is " + result)
        }
        printingResult.call(result)
        LOGGER.info(() -> "the result is " + result)
        return result
    }
}
