package by.siarhei.information.interpreter;

public abstract class AbstractMathExpression {
    private int priority;

    public abstract void interpret(Context context);

    public void setPriority(int priority){
        this.priority = priority;
    }

    public int getPriority(){
        return this.priority;
    }

}
