package by.siarhei.information.composite.impl;

import by.siarhei.information.composite.api.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class ComposedParagraph implements TextComponent {
    List<TextComponent> sentences = new ArrayList<>();

    @Override
    public int count() {
        return 1;
    }

    @Override
    public boolean addChild(TextComponent component) {
        return sentences.add(component);
    }

    @Override
    public String toString() {
        StringBuilder paragraph = new StringBuilder();
        paragraph.append("  ");
        sentences.forEach(symbol -> paragraph.append(symbol.toString()));
        paragraph.append("\n");
        return paragraph.toString();
    }
}
