package by.siarhei.information.parser;

import by.siarhei.information.composite.ComponentType;
import by.siarhei.information.composite.TextComponent;
import by.siarhei.information.composite.impl.TextComposite;

import java.util.Arrays;

public class SentenceToLexemParser extends AbstractParser {

    private static final String REGEX_LEXEM = " ";


    @Override
    public void fillComponent(TextComponent textComponent, String text) {
        String[] subLines = text.split(REGEX_LEXEM);
        Arrays.stream(subLines).forEachOrdered(subLine -> {
            TextComponent component = new TextComposite(ComponentType.LEXEM);
            if (hasNext()) {
                getNextParser().fillComponent(component, subLine);
            }
            textComponent.addChild(component);
        });
    }
}
