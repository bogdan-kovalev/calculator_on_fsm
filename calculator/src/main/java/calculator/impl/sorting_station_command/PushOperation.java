package calculator.impl.sorting_station_command;

import calculator.impl.EvaluationCommand;
import calculator.impl.EvaluationStack;
import calculator.impl.math_operation.AbstractOperation;

/**
 * @author Bogdan Kovalev
 */
public class PushOperation implements EvaluationCommand {

    private final AbstractOperation operation;

    public PushOperation(AbstractOperation operation) {
        this.operation = operation;
    }

    @Override
    public void evaluate(EvaluationStack stack) {
        stack.pushOperation(operation);
    }
}
