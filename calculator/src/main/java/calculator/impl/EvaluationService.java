package calculator.impl;

import calculator.EvaluationException;
import calculator.impl.parser.*;
import fsm.StateAcceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static calculator.impl.State.*;

public class EvaluationService implements StateAcceptor<State, EvaluationContext, EvaluationException> {

    private static final Logger LOG = Logger.getLogger(EvaluationService.class.getName());

    {
        LOG.setLevel(Level.OFF);
    }

    private final Map<State, MathExpressionParser> parsers = new HashMap<State, MathExpressionParser>() {{
        put(NUMBER, new NumberParser());
        put(BINARY_OP, new MathOperationParser());
        put(FUNCTION, new MathFunctionParser());
        put(ARGUMENT_SEPARATOR, new ArgumentSeparatorParser());
        put(LEFT_PARENTHESES, new LeftParenthesesParser());
        put(RIGHT_PARENTHESES, new RightParenthesesParser());
        put(FINISH, new EndOfExpressionParser());
    }};


    @Override
    public boolean acceptState(EvaluationContext context, State possibleState) throws EvaluationException {
        if (LOG.isLoggable(Level.INFO))
            LOG.info("Trying to accept " + possibleState.name());

        final MathExpressionParser parser = parsers.get(possibleState);

        if (parser == null) {
            throw new IllegalStateException("Parser not found for state: " + possibleState);
        }

        context.getExpressionReader().skipWhitespaces();

        final EvaluationCommand evaluationCommand = parser.parse(context);
        if (evaluationCommand == null) {
            return false;
        }
        evaluationCommand.evaluate(context.getEvaluationStack());

        if (LOG.isLoggable(Level.INFO))
            LOG.info(possibleState.name() + " was accepted");
        return true;
    }
}
