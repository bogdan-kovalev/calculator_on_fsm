package calculator.impl.parser;

import calculator.impl.EvaluationCommand;
import calculator.impl.EvaluationContext;
import calculator.impl.MathExpressionParser;
import calculator.impl.MathExpressionReader;
import calculator.impl.sorting_station_command.Flush;

public class EndOfExpressionParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(EvaluationContext context) {
        final MathExpressionReader reader = context.getExpressionReader();

        if (!reader.endOfExpression()) {
            return null;
        }

        return new Flush(reader.getIndex());
    }
}
