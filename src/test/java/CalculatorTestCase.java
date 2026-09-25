import com.uem.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTestCase {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void multiplyTwoNumbers() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    void multiplyByZero() {
        assertEquals(0, calculator.multiply(7, 0));
    }

    @Test
    void multiplyNegativeNumbers() {
        assertEquals(-6, calculator.multiply(-2, 3));
        assertEquals(6, calculator.multiply(-2, -3));
    }

    @Test
    void concatTwoStrings() {
        assertEquals("Hola mundo", calculator.concat("Hola ", "mundo"));
    }

    @Test
    void concatWithNullReturnsEmpty() {
        assertEquals("empty", calculator.concat(null, "mundo"));
        assertEquals("empty", calculator.concat("Hola", null));
    }

    @Test
    void sumTwoNumbers() {
        assertEquals(5.5, calculator.sum(2.0, 3.5), 0.000001);
    }

    @Test
    void sumWithNegativeNumbers() {
        assertEquals(-1.5, calculator.sum(-3.0, 1.5), 0.000001);
    }

    @Test
    void discountValidPercentage() {
        assertEquals(75.0, calculator.discount(100.0, 25.0), 0.000001);
    }

    @Test
    void discountZeroAndOneHundredPercent() {
        assertEquals(100.0, calculator.discount(100.0, 0.0), 0.000001);
        assertEquals(0.0, calculator.discount(100.0, 100.0), 0.000001);
    }

    @Test
    void discountInvalidPercentageThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.discount(100.0, -1.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.discount(100.0, 101.0));
    }

    @Test
    void calculateTotalOfAmounts() {
        assertEquals(35.75, calculator.calculateTotal(List.of(10.0, 20.5, 5.25)), 0.000001);
    }

    @Test
    void calculateTotalOfEmptyList() {
        assertEquals(0.0, calculator.calculateTotal(List.of()), 0.000001);
    }
}
