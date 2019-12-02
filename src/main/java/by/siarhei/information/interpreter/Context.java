package by.siarhei.information.interpreter;


import java.util.Deque;
import java.util.LinkedList;

public class Context {

    private Deque<Integer> contextValues = new LinkedList<>();

    public Integer popValue() {
        return contextValues.pop();
    }

    public void pushValue(Integer value) {
        this.contextValues.push(value);
    }
}
