package by.siarhei.information.interpreter;

public class TerminalExpressionBitRightShift extends AbstractMathExpression {

    TerminalExpressionBitRightShift(){
        setType(ExpressionType.BIT_RIGHT_SHIFT);
    }
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() >> context.popValue());
    }
}
