package by.siarhei.information.interpreter;

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

    private Deque<AbstractMathExpression> listExpression;

    private boolean isPreviousSymbolBitNot = false;
    private boolean isPreviousSymbolShift = false;

    public Client(String expression) {
        listExpression = new LinkedList<>();
        parseToExpressions(expression);
    }

    private void parseToExpressions(String expression) {
        int count = 0;
        StringBuilder digit = new StringBuilder();
        Deque<AbstractMathExpression> stack = new LinkedList<>();
        AbstractMathExpression temp;
        for (String symbol : expression.split("")) {
            count++;
            if (symbol.isEmpty()) {
                continue;
            }
            switch (symbol) {
                case BIT_OR:
                    pushDigit(digit);
                    stack.push(new TerminalExpressionBitOR());
                    break;
                case BIT_AND:
                    pushDigit(digit);
                    stack.push(new TerminalExpressionBitAND());
                    break;
                case BIT_XOR:
                    pushDigit(digit);
                    stack.push(new TerminalExpressionBitXOR());
                    break;
                case BIT_NOT:
                    pushDigit(digit);
                    listExpression.add(new TerminalExpressionBitNOT());
                    isPreviousSymbolBitNot = true;
                    break;
                case BIT_RIGHT_SHIFT:
                    if (isPreviousSymbolShift) {
                        stack.push(new TerminalExpressionBitRightShift());
                        isPreviousSymbolShift = false;
                    } else {
                        isPreviousSymbolShift = true;
                    }
                    pushDigit(digit);
                    break;
                case BIT_LEFT_SHIFT:
                    if (isPreviousSymbolShift) {
                        stack.push(new TerminalExpressionBitLeftShift());
                        isPreviousSymbolShift = false;
                    } else {
                        isPreviousSymbolShift = true;
                    }
                    pushDigit(digit);
                    isPreviousSymbolShift = true;
                    break;
                case BORDER_OPEN:
                    pushDigit(digit);
                    stack.push(new NonTerminalExpressionBorderOpen());
                    break;
                case BORDER_CLOSE:
                    pushDigit(digit);
                    while (!(temp = stack.pop()).getType().equals(ExpressionType.BORDER_OPEN)) {
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
        listExpression.addAll(stack);
    }

    private void pushDigit(StringBuilder digit) {
        if (digit.toString().length() > 0) {
            if (isPreviousSymbolBitNot) {
                AbstractMathExpression temp = listExpression.getLast();
                listExpression.removeLast();
                addNumberExpression(digit);
                listExpression.add(temp);
                isPreviousSymbolBitNot = false;
            } else {
                addNumberExpression(digit);
            }
        }
    }

    public String calculate() {
        Context context = new Context();
        for (AbstractMathExpression expression : listExpression) {
            System.out.println(context.toString());
            expression.interpret(context);
        }
        return String.valueOf(context.popValue());
    }

    private void addNumberExpression(StringBuilder digit) {
        listExpression.add(
                new NonTerminalExpressionNumber(Integer.valueOf(digit.toString())));
        digit.delete(0, digit.toString().length());
    }
}

