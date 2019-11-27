package by.siarhei.information.parser;

import by.siarhei.information.composite.api.TextComponent;
import by.siarhei.information.composite.impl.ComposedParagraph;

import java.util.Arrays;

public class InputTextToParagraphParser extends AbstractParser {

    private static final String REGEX_PARAGRAPH = "[ ]{2,}";

    @Override
    public void fillComponent(TextComponent textComponent, String text) {
        String[] subLines = text.split(REGEX_PARAGRAPH);
        Arrays.stream(subLines).forEachOrdered(subLine -> {
            if (!subLine.isBlank()) {
                TextComponent component = new ComposedParagraph();
                if (hasNext()) {
                    getNextParser().fillComponent(component, subLine);
                }
                textComponent.addChild(component);
            }
        });
    }
}
