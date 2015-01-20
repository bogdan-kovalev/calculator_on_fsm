package calculator.impl;

import fsm.TransitionMatrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static calculator.impl.State.*;
import static java.util.EnumSet.noneOf;
import static java.util.EnumSet.of;

public class EvaluationMatrix implements TransitionMatrix<State> {

    private final Map<State, Set<State>> transitions = new HashMap<State, Set<State>>() {{
        put(START, of(NUMBER, FUNCTION, LEFT_PARENTHESES));
        put(NUMBER, of(BINARY_OP, ARGUMENT_SEPARATOR, RIGHT_PARENTHESES, FINISH));
        put(BINARY_OP, of(NUMBER, FUNCTION, LEFT_PARENTHESES));
        put(FUNCTION, of(LEFT_PARENTHESES));
        put(ARGUMENT_SEPARATOR, of(NUMBER, FUNCTION));
        put(LEFT_PARENTHESES, of(LEFT_PARENTHESES, NUMBER, FUNCTION));
        put(RIGHT_PARENTHESES, of(RIGHT_PARENTHESES, ARGUMENT_SEPARATOR, BINARY_OP, FINISH));
        put(FINISH, noneOf(State.class));
    }};

    @Override
    public State getStartState() {
        return START;
    }

    @Override
    public State getFinishState() {
        return FINISH;
    }

    @Override
    public Set<State> getPossibleStates(State state) {
        return transitions.get(state);
    }
}
