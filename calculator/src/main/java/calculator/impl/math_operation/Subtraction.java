package calculator.impl.math_operation;

import calculator.impl.EvaluationStack;

import java.util.Deque;

/**
 * @author Bogdan Kovalev
 */
public class Subtraction extends AbstractOperation {

    @Override
    public void evaluate(EvaluationStack stack) {
        final Deque<Double> operandStack = stack.getOperandStack();
        Double operand2 = operandStack.pop();
        Double operand1 = operandStack.pop();
        operandStack.push(operand1 - operand2);
    }
}
