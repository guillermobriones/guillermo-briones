import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import com.uem.Calculator;
public class CalculatorTestCase {
    public Calculator calculator;
    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }
    @Test
    public void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
    }
}
