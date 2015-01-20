package calculator;

import calculator.impl.StateMachineCalculator;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Bogdan Kovalev
 */
public class Tests {

    private final static Logger LOG = Logger.getLogger(Tests.class.getName());

    @Test
    public void testEvaluateNumber() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testEvaluateNumber #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("509.42");
        final double expectedResult = 509.42;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }

    @Test
    public void testAddition() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testAddition #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("1.5+3.5");
        final double expectedResult = 5;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }

    @Test
    public void testSubtraction() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testSubtraction #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("7.7-3.7");
        final double expectedResult = 4;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }

    @Test
    public void testMultiAddition() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testMultiAddition #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("2+4+8");
        final double expectedResult = 14;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }

    @Test
    public void testSimpleParentheses() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testSimpleParentheses #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("(2+2)*2");
        final double expectedResult = 8;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }

    @Test
    public void testInnerParentheses() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testInnerParentheses #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("2+(2+2*(2+2))");
        final double expectedResult = 12;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }

    @Test
    public void testMultiplication() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testMultiplication #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("2*8");
        final double expectedResult = 16;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }

    @Test
    public void testDivision() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testDivision #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("24/3");
        final double expectedResult = 8;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }

    @Test
    public void testExponentiation() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testExponentiation #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("2^3^2");
        final double expectedResult = 512;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }


    @Test
    public void testRootFunction() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testRootFunction #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("sqrt(sqrt(16))");
        final double expectedResult = 2;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }

    @Test
    public void testSumFunction() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testSumFunction #########\n");

        StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult1 = calculator.evaluate("sum(5+1;sum(10;4;16;70))");
        final double expectedResult1 = 106;

        assertTrue("actual: " + actualResult1 + " expected: " + expectedResult1,
                expectedResult1 == actualResult1);
    }

    @Test
    public void testComplexTest() throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testComplexTest #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double actualResult = calculator.evaluate("2 + (2 / (sqrt(sqrt(16)) + 2) * 2 / (2^(2+2))) + sum(10;4;16;70)");
        final double expectedResult = 102.0625;

        assertTrue("actual: " + actualResult + " expected: " + expectedResult,
                expectedResult == actualResult);
    }

    @Test
    public void testRightParenthesesExpectedException() {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testRightParenthesesExpectedException #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        try {
            calculator.evaluate("1-(1+2");
            assertFalse(true);
        } catch (EvaluationException e) {
            assertTrue(e.getMessage().equals("Closing bracket expected."));
        }
    }

    @Test
    public void testLeftParenthesesExpectedException() {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testLeftParenthesesExpectedException #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        try {
            calculator.evaluate("1-1+2)");
            assertFalse(true);
        } catch (EvaluationException e) {
            assertTrue(e.getMessage().equals("Opening bracket expected."));
        }
    }

    @Test
    public void testUnexpectedSymbol() {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("\n######### testUnexpectedSymbol #########\n");

        final StateMachineCalculator calculator = new StateMachineCalculator();
        try {
            calculator.evaluate("1-;+2");
            assertFalse(true);
        } catch (EvaluationException e) {
            assertTrue(e.getErrorIndex() == 2);
        }
    }

}
