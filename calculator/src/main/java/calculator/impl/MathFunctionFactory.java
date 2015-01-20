package calculator.impl;

import calculator.impl.math_operation.function.AbstractFunction;
import calculator.impl.math_operation.function.SquareRoot;
import calculator.impl.math_operation.function.Sum;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Bogdan Kovalev
 */
public class MathFunctionFactory {

    private final Map<String, FunctionCreator> creators =
            new HashMap<String, FunctionCreator>() {{
                put(MathFunctions.SQUARE_ROOT.getPresentation(), new FunctionCreator() {
                    @Override
                    public AbstractFunction createFunction() {
                        return new SquareRoot();
                    }
                });
                put(MathFunctions.SUM.getPresentation(), new FunctionCreator() {
                    @Override
                    public AbstractFunction createFunction() {
                        return new Sum();
                    }
                });
            }};

    public AbstractFunction create(String presentation) {
        return creators.get(presentation).createFunction();
    }

    public Set<String> getAvailableFunctionPresentations() {
        return creators.keySet();
    }

    enum MathFunctions {
        SUM("sum"),
        SQUARE_ROOT("sqrt");

        private final String presentation;

        MathFunctions(String presentation) {
            this.presentation = presentation;
        }

        public String getPresentation() {
            return presentation;
        }
    }

    private static interface FunctionCreator {
        AbstractFunction createFunction();
    }
}
