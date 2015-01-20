package calculator.impl.sorting_station_command;

import calculator.EvaluationException;
import calculator.impl.EvaluationCommand;
import calculator.impl.EvaluationStack;
import calculator.impl.math_operation.AbstractOperation;

import java.util.Deque;

/**
 * @author Bogdan Kovalev.
 */
public class Flush implements EvaluationCommand {
    private final int lastParseIndex;

    public Flush(int lastParseIndex) {
        this.lastParseIndex = lastParseIndex;
    }

    @Override
    public void evaluate(EvaluationStack stack) throws EvaluationException {
        if (!stack.getBracketStack().isEmpty()) {
            throw new EvaluationException("Closing bracket expected.", lastParseIndex);
        }

        final Deque<AbstractOperation> operationStack = stack.getOperationStack();
        while (!operationStack.isEmpty()) {
            operationStack.pop().evaluate(stack);
        }
    }
}
