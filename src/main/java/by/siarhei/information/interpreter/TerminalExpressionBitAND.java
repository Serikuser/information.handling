package by.siarhei.information.interpreter;

public class TerminalExpressionBitAND extends AbstractMathExpression {

    TerminalExpressionBitAND() {
        setType(ExpressionType.BIT_AND);
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() & context.popValue());
    }
}
