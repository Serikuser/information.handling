package by.siarhei.information.interpreter;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InterpreterTest {
    private Client interpreter;
    private String expression;

    @BeforeClass
    private void setUp() {
        expression = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";
        interpreter = new Client(expression);
    }

    @AfterClass
    private void setDown() {
        expression = null;
        interpreter = null;
    }

    @Test
    private void bitExpressionInterpretationTestPositive() {
        int expected = 5 | (1 & 2 & (3 | (4 & (1 ^ 5 | 6 & 47) | 3) | (~89 & 4 | (42 & 7))) | 1);
        int actual = Integer.valueOf(interpreter.calculate());
        Assert.assertEquals(actual, expected);
    }

    @Test
    private void bitExpressionInterpretationTestNegative() {
        int expected = (1 & 2 & (3 | (4 & (1 ^ 5 | 6 & 47) | 3)) | 1);
        int actual = Integer.valueOf(interpreter.calculate());
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    private void bitExpressionWithInvalidExpressionInterpretationTestNegative() {
        interpreter = new Client("asd");
    }
}
