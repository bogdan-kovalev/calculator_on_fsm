package calculator.impl.parser;

import calculator.impl.*;
import calculator.impl.math_operation.AbstractOperation;
import calculator.impl.sorting_station_command.PushOperation;

/**
 * @author Bogdan Kovalev
 */
public class MathOperationParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader expressionReader = context.getExpressionReader();
        final String remainingExpression = expressionReader.getRemainingExpression();
        final MathOperationFactory mathOperationFactory = context.getMathOperationFactory();

        for (String presentation : mathOperationFactory.getAvailableOperationPresentations()) {

            if (remainingExpression.startsWith(presentation)) {
                expressionReader.incrementIndex(presentation.length());

                final AbstractOperation operation = mathOperationFactory.create(presentation);

                return new PushOperation(operation);
            }
        }

        return null;
    }
}
