package task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MultipleOfANumberTesterUnitTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @ParameterizedTest
    @MethodSource("task.TestDataGenerator#loadTestData")
    public void testMain_DivisibleNumbers(String studentPath) {
        String input = "10" + System.lineSeparator() + "5" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Kontrola, či trieda obsahuje metódu main
        try {
            Class<?> studentClass = Class.forName(studentPath);
            Method mainMethod = studentClass.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[]{});
        } catch (NoSuchMethodException e) {
            fail("Trieda neobsahuje metódu main: " + studentPath);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Testovanie zlyhalo pre študenta s cestou: " + studentPath);
        }

        assertEquals("Enter two numbers: 10 is divisible by the number 5 and the result of the division is: 2", outContent.toString().trim());
    }

    @ParameterizedTest
    @MethodSource("task.TestDataGenerator#loadTestData")
    public void testMain_NotDivisibleNumbers(String studentPath) {
        String input = "10" + System.lineSeparator() + "3" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Kontrola, či trieda obsahuje metódu main
        try {
            Class<?> studentClass = Class.forName(studentPath);
            Method mainMethod = studentClass.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[]{});
        } catch (NoSuchMethodException e) {
            fail("Trieda neobsahuje metódu main: " + studentPath);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Testovanie zlyhalo pre študenta s cestou: " + studentPath);
        }

        assertEquals("Enter two numbers: 10 is not divisible by the number 3", outContent.toString().trim());
    }



}
