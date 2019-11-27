package by.siarhei.information.parser;

import by.siarhei.information.composite.api.TextComponent;
import by.siarhei.information.composite.impl.ComposedSentence;

import java.util.Arrays;

public class ParagraphToSentenceParser extends AbstractParser {

    private static final String REGEX_SENTENCE = "(?<=[a-z][.!?])\\s+";


    @Override
    public void fillComponent(TextComponent textComponent, String text) {
        String[] subLines = text.split(REGEX_SENTENCE);
        Arrays.stream(subLines).forEachOrdered(subLine -> {
            TextComponent component = new ComposedSentence();
            if (hasNext()) {
                getNextParser().fillComponent(component, subLine);
            }
            textComponent.addChild(component);
        });
    }
}
