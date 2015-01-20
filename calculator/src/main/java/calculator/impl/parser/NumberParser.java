package calculator.impl.parser;

import calculator.impl.EvaluationCommand;
import calculator.impl.EvaluationContext;
import calculator.impl.MathExpressionParser;
import calculator.impl.MathExpressionReader;
import calculator.impl.sorting_station_command.PushNumber;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class NumberParser implements MathExpressionParser {

    private final NumberFormat numberFormat = DecimalFormat.getNumberInstance(Locale.US);

    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader expressionReader = context.getExpressionReader();
        final String mathExpression = expressionReader.getMathExpression();
        final int index = expressionReader.getIndex();

        final ParsePosition parsePosition = new ParsePosition(index);
        final Number number = numberFormat.parse(mathExpression, parsePosition);
        if (parsePosition.getErrorIndex() != -1) {
            return null;
        }

        expressionReader.setIndex(parsePosition.getIndex());

        return new PushNumber(number);
    }
}
