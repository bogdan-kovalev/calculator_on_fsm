package calculator.impl;

import calculator.EvaluationException;
import fsm.StateAcceptor;
import fsm.StateMachineContext;
import fsm.TransitionMatrix;

public class EvaluationContext implements StateMachineContext<State, EvaluationContext, EvaluationException> {

    private final EvaluationMatrix matrix = new EvaluationMatrix();
    private final EvaluationService evaluationService = new EvaluationService();

    private final EvaluationStack evaluationStack = new EvaluationStack();

    private final MathOperationFactory mathOperationFactory = new MathOperationFactory();
    private final MathFunctionFactory mathFunctionFactory = new MathFunctionFactory();

    private final MathExpressionReader mathExpressionReader;

    public EvaluationContext(String mathExpression) {
        mathExpressionReader = new MathExpressionReader(mathExpression);
    }

    public MathExpressionReader getExpressionReader() {
        return mathExpressionReader;
    }

    public EvaluationStack getEvaluationStack() {
        return evaluationStack;
    }

    public MathOperationFactory getMathOperationFactory() {
        return mathOperationFactory;
    }

    public MathFunctionFactory getMathFunctionFactory() {
        return mathFunctionFactory;
    }

    @Override
    public TransitionMatrix<State> getTransitionMatrix() {
        return matrix;
    }

    @Override
    public StateAcceptor<State, EvaluationContext, EvaluationException> getStateAcceptor() {
        return evaluationService;
    }
}
