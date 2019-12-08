package by.siarhei.information.composite.impl;

import by.siarhei.information.composite.ComponentType;
import by.siarhei.information.composite.TextComponent;

import java.util.List;

public class TextSymbol implements TextComponent {

    private char symbol;
    private ComponentType type;

    public TextSymbol() {
        this.type = ComponentType.SYMBOL;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean addChild(TextComponent component) {
        return false;
    }

    @Override
    public void removeChild(TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TextComponent> getChildrenList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ComponentType getComponentType() {
        return this.type;
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TextSymbol that = (TextSymbol) o;
        return symbol == that.symbol;
    }

    @Override
    public int hashCode() {
        return (int) symbol;
    }
}
