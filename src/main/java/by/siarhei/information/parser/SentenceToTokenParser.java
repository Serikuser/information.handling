package by.siarhei.information.parser;

import by.siarhei.information.composite.api.TextComponent;
import by.siarhei.information.composite.impl.ComposedToken;

import java.util.Arrays;

public class SentenceToTokenParser extends AbstractParser {

    private static final String REGEX_TOKEN = " ";


    @Override
    public void fillComponent(TextComponent textComponent, String text) {
        String[] subLines = text.split(REGEX_TOKEN);
        Arrays.stream(subLines).forEachOrdered(subLine -> {
            TextComponent component = new ComposedToken();
            if (hasNext()) {
                getNextParser().fillComponent(component, subLine);
            }
            textComponent.addChild(component);
        });
    }
}
