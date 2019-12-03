package by.siarhei.information.interpreter;

public class TerminalExpressionBitNOT extends AbstractMathExpression {

    TerminalExpressionBitNOT() {
        setType(ExpressionType.BIT_NOT);
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(~context.popValue());
    }

}
