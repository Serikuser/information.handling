package by.siarhei.information.composite.impl;

import by.siarhei.information.composite.api.TextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComposedText implements TextComponent {

    private List<TextComponent> paragraphs = new ArrayList<>();

    @Override
    public int count() {
        return 0;
    }

    @Override
    public boolean addChild(TextComponent component) {
        return paragraphs.add(component);
    }

    @Override
    public List<TextComponent> getUnmodifiedComponentList() {
        return Collections.unmodifiableList(paragraphs);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        paragraphs.forEach(symbol -> text.append(symbol.toString()));
        return text.toString();
    }

    public void setParagraphs(List<TextComponent> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public List<TextComponent> getParagraphs() {
        return paragraphs;
    }
}
