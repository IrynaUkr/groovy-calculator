/**
 *  Calculator
 *
 */

class Calculator {

    static int calculate(String input) {

        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException()
        }

        int currentNumber = 0
        char operator = '+'

        def stripped = input.replace(' ', '')
        def makeArray = stripped.&toCharArray
        def inputArray = makeArray()

        Stack<Integer> storageNumbers = new Stack<>()
        for (int i = 0; i < inputArray.length; i++) {
            if (Character.isDigit(inputArray[i])) {
                currentNumber = currentNumber * 10 + (inputArray[i] - 48)
            }

            if (!Character.isDigit(inputArray[i]) || i == inputArray.length - 1) {
                switch (operator) {
                    case '+': storageNumbers.push(currentNumber)
                        break
                    case '-': storageNumbers.push(-currentNumber)
                        break
                    case '*': storageNumbers.push ( storageNumbers.pop ( ) * currentNumber)
                        break
                    case '/': storageNumbers.push(storageNumbers.pop() / currentNumber as Integer)
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

    private static int addingNumbersInStorage(Stack<Integer> storageNumbers) {
        def printingResult = { param -> println("the result is : ${param}") }
        int result = 0
        while (!storageNumbers.isEmpty())
            result += storageNumbers.pop()
        println(result)
        printingResult.call(result)

        return result
    }
}