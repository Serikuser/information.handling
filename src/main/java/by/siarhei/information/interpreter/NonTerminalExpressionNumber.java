package by.siarhei.information.interpreter;

public class NonTerminalExpressionNumber extends AbstractMathExpression {
    private int number;

    public NonTerminalExpressionNumber(int number,int priority) {
        setPriority(priority);
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
