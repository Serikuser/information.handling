package by.siarhei.information.composite.impl;

import by.siarhei.information.composite.api.TextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComposedToken implements TextComponent {
    private List<TextComponent> symbols = new ArrayList<>();

    @Override
    public List<TextComponent> getChildrenList() {
        return symbols;
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public boolean addChild(TextComponent component) {
        return symbols.add(component);
    }

    @Override
    public List<TextComponent> getUnmodifiedComponentList() {
        return Collections.unmodifiableList(symbols);
    }

    @Override
    public void removeChild(TextComponent symbol) {
        symbols.remove(symbol);
    }

    @Override
    public String toString() {
        StringBuilder token = new StringBuilder();
        token.append(" ");
        symbols.forEach(symbol -> token.append(symbol.toString()));
        return token.toString();
    }
}
