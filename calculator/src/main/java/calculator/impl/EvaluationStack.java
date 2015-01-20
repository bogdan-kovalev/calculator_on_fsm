package calculator.impl;

import calculator.impl.math_operation.AbstractOperation;
import calculator.impl.math_operation.function.AbstractFunction;
import calculator.impl.util.MathAssociativity;

import java.util.ArrayDeque;
import java.util.Deque;

import static calculator.impl.util.MathAssociativity.LEFT;
import static calculator.impl.util.MathAssociativity.RIGHT;

public class EvaluationStack {
    private final Deque<Double> operandStack = new ArrayDeque<Double>();
    private final Deque<AbstractOperation> operationStack = new ArrayDeque<>();
    private final Deque<Integer> bracketStack = new ArrayDeque<Integer>();

    public Deque<AbstractOperation> getOperationStack() {
        return operationStack;
    }

    public Deque<Double> getOperandStack() {
        return operandStack;
    }

    public void pushArgumentSeparator() {

        while (operationStack.size() > bracketStack.peek()) {
            operationStack.pop().evaluate(this);
        }

        final AbstractFunction currentFunction = (AbstractFunction) operationStack.peek();
        currentFunction.incrementArgumentsCounter();
    }

    public void pushLeftParentheses() {
        bracketStack.push(operationStack.size());
    }

    public void pushNumber(Number number) {
        operandStack.push(number.doubleValue());
    }

    public void pushOperation(AbstractOperation operation) {

        Integer size = 0;
        if (!bracketStack.isEmpty())
            size = bracketStack.peek();

        while (operationStack.size() != size && lessPriority(operation)) {
            operationStack.pop().evaluate(this);
        }

        operationStack.push(operation);
    }

    private boolean lessPriority(AbstractOperation operation) {
        final int precedence = operation.PRECEDENCE;
        final MathAssociativity associativity = operation.ASSOCIATIVITY;

        return (
                (associativity == LEFT & precedence <= operationStack.peek().PRECEDENCE)
                        |
                        (associativity == RIGHT & precedence < operationStack.peek().PRECEDENCE)
        );
    }

    public void pushRightParentheses() {
        final Integer operatorStackSize = bracketStack.pop();

        while (operationStack.size() > operatorStackSize) {
            operationStack.pop().evaluate(this);
        }
    }

    public Deque<Integer> getBracketStack() {
        return bracketStack;
    }
}
