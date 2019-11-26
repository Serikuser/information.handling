package by.siarhei.information.composite.impl;

import by.siarhei.information.composite.api.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class ComposedText implements TextComponent {
    List<TextComponent> paragraphs = new ArrayList<>();

    @Override
    public int count() {
        return 0;
    }

    @Override
    public boolean addChild(TextComponent component) {
        return paragraphs.add(component);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        paragraphs.forEach(symbol-> text.append(symbol.toString()));
        return text.toString();
    }
}
