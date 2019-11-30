package by.siarhei.information.interpreter;

public class TerminalExpressionBitOR extends AbstractMathExpression {

    TerminalExpressionBitOR(int priority){
        setPriority(priority);
    }
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() | context.popValue());
    }
}
