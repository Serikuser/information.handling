package by.siarhei.information.interpreter;

public abstract class AbstractMathExpression {

    private ExpressionType type;

    @Override
    public String toString() {
        return type.name();
    }
    public abstract void interpret(Context context);

    public ExpressionType getType() {
        return type;
    }

    public void setType(ExpressionType type) {
        this.type = type;
    }
}
