package by.siarhei.information.interpreter;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Client {

    private static final String BIT_OR = "|";
    private static final String BIT_AND = "&";
    private static final String BIT_XOR = "^";
    private static final String BIT_NOT = "~";
    private static final String BIT_RIGHT_SHIFT = ">";
    private static final String BIT_LEFT_SHIFT = "<";
    private static final String BORDER_OPEN = "(";
    private static final String BORDER_CLOSE = ")";

    private ArrayList<AbstractMathExpression> listExpression;

    public Client(String expression) {
        listExpression = new ArrayList<>();
        parseToExpressions(expression);
    }

    private void parseToExpressions(String expression) {
        int count = 0;
        StringBuilder digit = new StringBuilder();
        boolean isPreviousSymbolShift = false;
        Deque<AbstractMathExpression> list = new LinkedList<>();
        AbstractMathExpression temp;
        for (String symbol : expression.split("")) {
            count++;
            if (symbol.isEmpty()) {
                continue;
            }
            switch (symbol) {
                case BIT_OR:
                    pushDigit(digit);
                    list.push(new TerminalExpressionBitOR());
                    break;
                case BIT_AND:
                    pushDigit(digit);
                    list.push(new TerminalExpressionBitAND());
                    break;
                case BIT_XOR:
                    pushDigit(digit);
                    list.push(new TerminalExpressionBitXOR());
                    break;
                case BIT_NOT:
                    pushDigit(digit);
                    list.push(new TerminalExpressionBitNOT());
                    break;
                case BIT_RIGHT_SHIFT:
                    if (isPreviousSymbolShift) {
                        list.push(new TerminalExpressionBitRightShift());
                        isPreviousSymbolShift = false;
                    } else {
                        isPreviousSymbolShift = true;
                    }
                    pushDigit(digit);
                    break;
                case BIT_LEFT_SHIFT:
                    if (isPreviousSymbolShift) {
                        list.push(new TerminalExpressionBitLeftShift());
                        isPreviousSymbolShift = false;
                    } else {
                        isPreviousSymbolShift = true;
                    }
                    pushDigit(digit);
                    isPreviousSymbolShift = true;
                    break;
                case BORDER_OPEN:
                    pushDigit(digit);
                    list.push(new NonTerminalExpressionBorderOpen());
                    break;
                case BORDER_CLOSE:
                    pushDigit(digit);
                    while (!(temp = list.pop()).getType().equals(ExpressionType.BORDER_OPEN)) {
                        listExpression.add(temp);
                    }
                    break;
                default:
                    digit.append(symbol);
                    if (count == expression.length()) {
                        pushDigit(digit);
                    }
            }
        }
        listExpression.addAll(list);
    }

    private void pushDigit(StringBuilder digit) {
        if (digit.toString().length() > 0) {
            listExpression.add(
                    new NonTerminalExpressionNumber(Integer.valueOf(digit.toString())));
            digit.delete(0, digit.toString().length());
        }
    }

    public Integer calculate() {
        Context context = new Context();
        for (AbstractMathExpression expression : listExpression) {
            expression.interpret(context);
        }
        return context.popValue();
    }
}

