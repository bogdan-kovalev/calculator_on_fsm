package calculator.impl.sorting_station_command;

import calculator.impl.EvaluationCommand;
import calculator.impl.EvaluationStack;

/**
 * @author Bogdan Kovalev
 */
public class PushNumber implements EvaluationCommand {

    private final Number number;

    public PushNumber(Number number) {
        this.number = number;
    }

    @Override
    public void evaluate(EvaluationStack stack) {
        stack.pushNumber(number);
    }
}
