package by.siarhei.information.interpreter;

public class TerminalExpressionBitNOT extends AbstractMathExpression {

    TerminalExpressionBitNOT(int priority){
        setPriority(priority);
    }
    @Override
    public void interpret(Context context) {
        context.pushValue(~context.popValue());
    }
}
