package calculator.impl;

public enum State {
    START,
    NUMBER,
    BINARY_OP,
    FUNCTION,
    ARGUMENT_SEPARATOR,
    LEFT_PARENTHESES,
    RIGHT_PARENTHESES,
    FINISH
}
