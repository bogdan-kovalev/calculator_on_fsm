package calculator.impl.parser;

import calculator.impl.EvaluationCommand;
import calculator.impl.EvaluationContext;
import calculator.impl.MathExpressionParser;
import calculator.impl.MathExpressionReader;
import calculator.impl.sorting_station_command.LeftParentheses;
import calculator.impl.util.MathSymbol;

/**
 * @author Bogdan Kovalev
 */
public class LeftParenthesesParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader expressionReader = context.getExpressionReader();

        if (expressionReader.endOfExpression() || expressionReader.currentChar() != MathSymbol.LEFT_PARENTHESES.getSymbol()) {
            return null;
        }

        expressionReader.incrementIndex(1);

        return new LeftParentheses();
    }
}
