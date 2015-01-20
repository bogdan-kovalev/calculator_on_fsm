package calculator.impl.sorting_station_command;

import calculator.impl.EvaluationCommand;
import calculator.impl.EvaluationStack;

/**
 * @author Bogdan Kovalev
 */
public class ArgumentSeparator implements EvaluationCommand {
    @Override
    public void evaluate(EvaluationStack stack) {
        stack.pushArgumentSeparator();
    }
}
