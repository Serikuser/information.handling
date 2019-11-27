package by.siarhei.information.composite.impl;

import by.siarhei.information.composite.api.TextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComposedSentence implements TextComponent {
    private List<TextComponent> tokens = new ArrayList<>();

    @Override
    public List<TextComponent> getChildrenList() {
        return tokens;
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public boolean addChild(TextComponent component) {
        return tokens.add(component);
    }

    @Override
    public List<TextComponent> getUnmodifiedComponentList() {
        return Collections.unmodifiableList(tokens);
    }

    @Override
    public void removeChild(TextComponent token) {
        tokens.remove(token);
    }

    @Override
    public String toString() {
        StringBuilder sentence = new StringBuilder();
        tokens.forEach(symbol -> sentence.append(symbol.toString()));
        return sentence.toString();
    }

}
