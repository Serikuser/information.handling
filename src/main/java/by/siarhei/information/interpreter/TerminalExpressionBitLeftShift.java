package by.siarhei.information.interpreter;

public class TerminalExpressionBitLeftShift extends AbstractMathExpression {

    TerminalExpressionBitLeftShift() {
        setType(ExpressionType.BIT_LEFT_SHIFT);
    }

    @Override
    public void interpret(Context context) {
        int second = context.popValue();
        int first = context.popValue();
        context.pushValue(first << second);
    }
}
