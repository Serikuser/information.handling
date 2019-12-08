package by.siarhei.information.interpreter;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InterpreterTest {

    @Test(dataProvider = "getExpressions")
    public void bitExpressionInterpretationTest(String expression, int expected) {
        Client interpreter = new Client(expression);
        int actual = Integer.parseInt(interpreter.calculate());
        Assert.assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] getExpressions() {
        return new Object[][]{
                {"5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)", 5},
                {"13<<2", 52},
                {"3>>5", 0},
                {"~6&9|(3&4)", 9},
                {"(7^5|1&2<<(2|5>>2&71))|1200", 1202},
                {"(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", 78}
        };
    }
}