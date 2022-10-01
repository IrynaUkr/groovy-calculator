import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.*;

class CalculatorTest {
    Calculator calculator;


    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @MethodSource("validArgument")
    void shouldReturnRightAnswerWithValidArgumentTest(String input, int expected) {

        assertEquals(expected, Calculator.calculate(input));
    }


    private static Stream<Arguments> validArgument() {
        return Stream.of(
                of("8", 8),
                of("8+2", 10),
                of("8-20", -12),
                of("10/3", 3),
                of("1+3*5", 16),
                of("20-6*9/3", 2)
        );
    }


    @ParameterizedTest
    @NullSource
    void shouldThrowIllegalArgumentExceptionWhenArgumentNullTest(String input) {

        assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate(input));
    }

    @ParameterizedTest
    @EmptySource
    void shouldThrowIllegalArgumentExceptionWhenArgumentEmptyStringTest(String input) {

        assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate(input));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenArgumentNoDigitTest() {

        assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate("input"));
    }
}