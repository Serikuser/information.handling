package by.siarhei.information.composite.impl;

import by.siarhei.information.composite.api.TextComponent;

import java.util.List;

public class TextSymbol implements TextComponent {

    private char symbol;

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol.charAt(0);
    }

    @Override
    public boolean addChild(TextComponent component) {
        return false;
    }

    @Override
    public List<TextComponent> getUnmodifiedComponentList() {
        throw new UnsupportedOperationException();
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
