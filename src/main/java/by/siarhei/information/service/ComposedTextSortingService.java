package by.siarhei.information.service;

import by.siarhei.information.composite.api.TextComponent;
import by.siarhei.information.composite.impl.ComposedText;

import java.util.List;

public class ComposedTextSortingService {

    public TextComponent sortComposedText(ComposedText text, CompareType type) {
        ComposedText sortedText = new ComposedText();
        List<TextComponent> list = text.getParagraphs();
        list.sort(type.getComparatorByType());
        sortedText.setParagraphs(list);
        return sortedText;
    }
}
