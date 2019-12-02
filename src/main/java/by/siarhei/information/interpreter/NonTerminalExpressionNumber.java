package by.siarhei.information.interpreter;

public class NonTerminalExpressionNumber extends AbstractMathExpression {
    private int number;

    public NonTerminalExpressionNumber(int number) {
        this.number = number;
        setType(ExpressionType.NUMBER);
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
