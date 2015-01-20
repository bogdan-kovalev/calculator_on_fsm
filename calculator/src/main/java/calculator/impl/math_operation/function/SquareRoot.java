package calculator.impl.math_operation.function;

import calculator.impl.EvaluationStack;

import java.util.Deque;

/**
 * @author Bogdan Kovalev
 */
public class SquareRoot extends AbstractFunction {
    @Override
    public void evaluate(EvaluationStack stack) {
        final Deque<Double> operandStack = stack.getOperandStack();
        Double operand = operandStack.pop();
        operandStack.push(Math.sqrt(operand));
    }
}
