package by.siarhei.information.interpreter;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private ArrayList<AbstractMathExpression> listExpression;

    public Client(String expression) {
        listExpression = new ArrayList<>();
        parse(expression);
    }
    //5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)

    private void parse(String expression) {
        int depth = 0;
        for (String symbol : expression.split("")) {
            if (symbol.isEmpty()) {
                continue;
            }
            switch (symbol) {
                case "|":
                    listExpression.add(new TerminalExpressionBitOR(depth));
                    break;
                case "&":
                    listExpression.add(new TerminalExpressionBitAND(depth));
                    break;
                case "^":
                    listExpression.add(new TerminalExpressionBitXOR(depth));
                    break;
                case "~":
                    listExpression.add(new TerminalExpressionBitNOT(depth));
                    break;
                case "(":
                    //change priority
                    depth++;
                    break;
                case ")":
                    //change priority
                    depth--;
                    break;
                default:
                    listExpression.add(
                            new NonTerminalExpressionNumber(Integer.valueOf(symbol),depth));

            }
        }
    }

    public Number calculate() {
        Context context = new Context();
        for (AbstractMathExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }

    public void sortExpressionByPriority(List<AbstractMathExpression> list) {
        list.sort((o1, o2)
                -> o2.getPriority() - o1.getPriority());
    }
}

