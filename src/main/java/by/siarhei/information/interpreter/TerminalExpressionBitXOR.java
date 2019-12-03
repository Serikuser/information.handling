package by.siarhei.information.interpreter;

public class TerminalExpressionBitXOR extends AbstractMathExpression {

    TerminalExpressionBitXOR() {
        setType(ExpressionType.BIT_XOR);
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() ^ context.popValue());
    }
}
