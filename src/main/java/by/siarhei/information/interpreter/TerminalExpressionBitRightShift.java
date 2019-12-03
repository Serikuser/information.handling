package by.siarhei.information.interpreter;

public class TerminalExpressionBitRightShift extends AbstractMathExpression {

    TerminalExpressionBitRightShift() {
        setType(ExpressionType.BIT_RIGHT_SHIFT);
    }

    @Override
    public void interpret(Context context) {
        int second = context.popValue();
        int first = context.popValue();
        context.pushValue(first >> second);
    }
}
