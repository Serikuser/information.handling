package by.siarhei.information.interpreter;

public class TerminalExpressionBitOR extends AbstractMathExpression {

    TerminalExpressionBitOR() {
        setType(ExpressionType.BIT_OR);
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() | context.popValue());
    }
}
