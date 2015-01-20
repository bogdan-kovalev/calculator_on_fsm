package calculator.impl.parser;

import calculator.impl.*;
import calculator.impl.math_operation.function.AbstractFunction;
import calculator.impl.sorting_station_command.PushOperation;

/**
 * @author Bogdan Kovalev
 */
public class MathFunctionParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader expressionReader = context.getExpressionReader();
        final String remainingExpression = expressionReader.getRemainingExpression();
        final MathFunctionFactory mathFunctionFactory = context.getMathFunctionFactory();

        for (String presentation : mathFunctionFactory.getAvailableFunctionPresentations()) {

            if (remainingExpression.startsWith(presentation)) {
                expressionReader.incrementIndex(presentation.length());

                final AbstractFunction function = mathFunctionFactory.create(presentation);
                return new PushOperation(function);
            }
        }

        return null;
    }
}
