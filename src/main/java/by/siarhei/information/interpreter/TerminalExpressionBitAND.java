package by.siarhei.information.interpreter;

public class TerminalExpressionBitAND extends AbstractMathExpression {

    TerminalExpressionBitAND(int priority){
        setPriority(priority);
    }
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() & context.popValue());
    }
}
