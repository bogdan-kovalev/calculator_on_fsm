package calculator.impl.sorting_station_command;

import calculator.EvaluationException;
import calculator.impl.EvaluationCommand;
import calculator.impl.EvaluationStack;
import calculator.impl.MathExpressionReader;

/**
 * @author Bogdan Kovalev
 */
public class RightParentheses implements EvaluationCommand {

    private final MathExpressionReader expressionReader;

    public RightParentheses(MathExpressionReader expressionReader) {
        this.expressionReader = expressionReader;
    }

    @Override
    public void evaluate(EvaluationStack stack) throws EvaluationException {

        if (stack.getBracketStack().isEmpty()) {
            throw new EvaluationException("Opening bracket expected.",
                    expressionReader.getIndex());
        }
        stack.pushRightParentheses();
    }
}
