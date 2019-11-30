package by.siarhei.information.interpreter;

public class TerminalExpressionBitXOR extends AbstractMathExpression {

    TerminalExpressionBitXOR(int priority){
        setPriority(priority);
    }
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() ^ context.popValue());
    }
}
