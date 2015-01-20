package calculator.impl.parser;

import calculator.impl.EvaluationCommand;
import calculator.impl.EvaluationContext;
import calculator.impl.MathExpressionParser;
import calculator.impl.MathExpressionReader;
import calculator.impl.sorting_station_command.ArgumentSeparator;
import calculator.impl.util.MathSymbol;

/**
 * @author Bogdan Kovalev
 */
public class ArgumentSeparatorParser implements MathExpressionParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader expressionReader = context.getExpressionReader();
        final int index = expressionReader.getIndex();

        final char argument_separator_symbol = MathSymbol.ARGUMENT_SEPARATOR.getSymbol();

        if (expressionReader.endOfExpression() || expressionReader.currentChar() != argument_separator_symbol) {
            return null;
        }

        expressionReader.setIndex(index + 1);

        return new ArgumentSeparator();
    }
}
