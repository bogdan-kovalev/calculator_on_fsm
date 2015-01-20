package calculator.impl.math_operation.function;

import calculator.impl.EvaluationStack;

import java.util.Deque;

/**
 * @author Bogdan Kovalev
 */
public class Sum extends AbstractFunction {
    @Override
    public void evaluate(EvaluationStack stack) {
        final Deque<Double> operandStack = stack.getOperandStack();
        Double result = 0.;
        for (int i = 0; i < argumentsCounter; i++) {
            result += operandStack.pop();
        }
        operandStack.push(result);
    }
}
