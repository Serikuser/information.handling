package by.siarhei.information.interpreter;

public class TerminalExpressionBitLeftShift extends AbstractMathExpression {

    TerminalExpressionBitLeftShift(){
        setType(ExpressionType.BIT_LEFT_SHIFT);
    }
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() << context.popValue());
    }
}
