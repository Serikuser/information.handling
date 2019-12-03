package by.siarhei.information.parser;

import by.siarhei.information.composite.TextComponent;
import by.siarhei.information.composite.impl.TextSymbol;
import by.siarhei.information.interpreter.Client;

import java.util.Arrays;

public class LexemToSymbolParser extends AbstractParser {
    private static final String REGEX_SYMBOL = "";
    private static final String REGEX_BIT_EXPRESSION = "[\"\\'\\^<>\\d()&|~]+";

    @Override
    public void fillComponent(TextComponent textComponent, String text) {
        if (text.matches(REGEX_BIT_EXPRESSION)) {
            Client interpreter = new Client(text);
            String calculatedExpression = interpreter.calculate();
            char[] charExp = calculatedExpression.toCharArray();
            for (char sybmol : charExp) {
                TextSymbol component = new TextSymbol();
                component.setSymbol(sybmol);
                if (hasNext()) {
                    getNextParser().fillComponent(component, String.valueOf(sybmol));
                }
                textComponent.addChild(component);
            }
        } else {
            String[] symbols = text.split(REGEX_SYMBOL);
            Arrays.stream(symbols).forEachOrdered(symbol -> {
                TextSymbol component = new TextSymbol();
                component.setSymbol(symbol.charAt(0));
                if (hasNext()) {
                    getNextParser().fillComponent(component, symbol);
                }
                textComponent.addChild(component);
            });
        }
    }
}
