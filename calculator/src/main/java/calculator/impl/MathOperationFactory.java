package calculator.impl;

import calculator.impl.math_operation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Bogdan Kovalev
 */
public class MathOperationFactory {

    private final Map<String, OperationCreator> creators =
            new HashMap<String, OperationCreator>() {{
                put(MathOperations.ADDITION.getPresentation(), new OperationCreator() {
                    @Override
                    public AbstractOperation createOperation() {
                        return new Addition();
                    }
                });
                put(MathOperations.SUBTRACTION.getPresentation(), new OperationCreator() {
                    @Override
                    public AbstractOperation createOperation() {
                        return new Subtraction();
                    }
                });
                put(MathOperations.MULTIPLICATION.getPresentation(), new OperationCreator() {
                    @Override
                    public AbstractOperation createOperation() {
                        return new Multiplication();
                    }
                });
                put(MathOperations.DIVISION.getPresentation(), new OperationCreator() {
                    @Override
                    public AbstractOperation createOperation() {
                        return new Division();
                    }
                });
                put(MathOperations.EXPONENTIATION.getPresentation(), new OperationCreator() {
                    @Override
                    public AbstractOperation createOperation() {
                        return new Exponentiation();
                    }
                });
            }};

    public AbstractOperation create(String presentation) {
        return creators.get(presentation).createOperation();
    }

    public Set<String> getAvailableOperationPresentations() {
        return creators.keySet();
    }

    enum MathOperations {
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("*"),
        DIVISION("/"),
        EXPONENTIATION("^");

        private final String presentation;

        MathOperations(String presentation) {
            this.presentation = presentation;
        }

        public String getPresentation() {
            return presentation;
        }
    }

    private static interface OperationCreator {
        AbstractOperation createOperation();
    }
}
