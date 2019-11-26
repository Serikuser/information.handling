package by.siarhei.information.composite.impl;

import by.siarhei.information.composite.api.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class ComposedSentence implements TextComponent {
    List<TextComponent> tokens = new ArrayList<>();

    @Override
    public int count() {
        return 1;
    }

    @Override
    public boolean addChild(TextComponent component) {
        return tokens.add(component);
    }

    @Override
    public String toString() {
        StringBuilder sentence = new StringBuilder();
        tokens.forEach(symbol-> sentence.append(symbol.toString()));
        sentence.append(".");
        return sentence.toString();
    }
}
