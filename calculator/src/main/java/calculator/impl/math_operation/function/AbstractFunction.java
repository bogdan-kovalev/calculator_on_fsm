package calculator.impl.math_operation.function;

import calculator.impl.math_operation.AbstractOperation;

/**
 * @author Bogdan Kovalev
 */
public abstract class AbstractFunction extends AbstractOperation {
    protected int argumentsCounter;

    public AbstractFunction() {
        this.argumentsCounter = 1;
    }

    public void incrementArgumentsCounter() {
        argumentsCounter++;
    }
}
