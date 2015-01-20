package calculator.impl.util;

/**
 * @author Bogdan Kovalev
 */
public enum MathSymbol {
    ARGUMENT_SEPARATOR(';'),
    LEFT_PARENTHESES('('),
    RIGHT_PARENTHESES(')');

    private final char symbol;

    MathSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
