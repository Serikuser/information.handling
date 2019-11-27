package by.siarhei.information.parser;

import by.siarhei.information.composite.api.TextComponent;
import by.siarhei.information.composite.impl.TextSymbol;

import java.util.Arrays;

public class TokenToSymbolParser extends AbstractParser {
    private static final String REGEX_SYMBOL = "";

    @Override
    public void fillComponent(TextComponent textComponent, String text) {
        String[] symbols = text.split(REGEX_SYMBOL);
        Arrays.stream(symbols).forEachOrdered(symbol -> {
            TextSymbol component = new TextSymbol();
            component.setSymbol(symbol);
            if (hasNext()) {
                getNextParser().fillComponent(component, symbol);
            }
            textComponent.addChild(component);
        });
    }
}
