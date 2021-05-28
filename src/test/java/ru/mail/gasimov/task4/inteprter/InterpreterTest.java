package ru.mail.gasimov.task4.inteprter;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.mail.gasimov.task4.exception.InformationHandlerException;
import ru.mail.gasimov.task4.interpreter.TextInterpreterContext;

public class InterpreterTest {
    TextInterpreterContext context;

    @BeforeClass
    public void setUp() {
        context = new TextInterpreterContext();
    }

    @DataProvider(name = "expression-provider")
    public Object[][] expressionProvider() {
        return new Object[][] {
                { "12|2^6", 12 | 2 ^ 6 },
                { "((6|2)<<2)>>2^4&3", ((6 | 2) << 2) >> 2 ^ 4 & 3 },
                { "4|1|7", 4 | 1 | 7 }
        };
    }

    @Test(dataProvider = "expression-provider")
    public void testEvaluate(String expression, int expected) throws InformationHandlerException {
        int actual = context.evaluate(expression);
        Assert.assertEquals(actual, expected);
    }
}
