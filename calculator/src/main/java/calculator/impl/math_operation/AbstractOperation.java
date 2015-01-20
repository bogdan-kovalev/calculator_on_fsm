package calculator.impl.math_operation;

import calculator.impl.EvaluationCommand;
import calculator.impl.EvaluationStack;
import calculator.impl.math_operation.function.SquareRoot;
import calculator.impl.math_operation.function.Sum;
import calculator.impl.util.MathAssociativity;
import calculator.impl.util.MathPrecedence;

import java.util.HashMap;
import java.util.Map;

import static calculator.impl.util.MathAssociativity.LEFT;
import static calculator.impl.util.MathAssociativity.RIGHT;
import static calculator.impl.util.MathPrecedence.*;

/**
 * @author Bogdan Kovalev
 */
public abstract class AbstractOperation implements EvaluationCommand {

    private static final Map<Class, MathPrecedence> mathOperationPrecedence = new HashMap<Class, MathPrecedence>() {{
        put(Addition.class, LOW);
        put(Subtraction.class, LOW);
        put(Multiplication.class, MEDIUM);
        put(Division.class, MEDIUM);
        put(Exponentiation.class, HIGH);
        put(SquareRoot.class, FUNCTION);
        put(Sum.class, FUNCTION);
    }};
    private static final Map<Class, MathAssociativity> mathOperationAssociativity = new HashMap<Class, MathAssociativity>() {{
        put(Addition.class, LEFT);
        put(Subtraction.class, LEFT);
        put(Multiplication.class, LEFT);
        put(Division.class, LEFT);
        put(Exponentiation.class, RIGHT);
        put(SquareRoot.class, LEFT);
        put(Sum.class, LEFT);
    }};

    public final int PRECEDENCE;
    public final MathAssociativity ASSOCIATIVITY;

    protected AbstractOperation() {
        PRECEDENCE = mathOperationPrecedence.get(this.getClass()).ordinal();
        ASSOCIATIVITY = mathOperationAssociativity.get(this.getClass());
    }

    @Override
    abstract public void evaluate(EvaluationStack stack);
}
