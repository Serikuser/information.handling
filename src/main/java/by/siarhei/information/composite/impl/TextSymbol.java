package by.siarhei.information.composite.impl;

import by.siarhei.information.composite.api.TextComponent;

public class TextSymbol implements TextComponent {

    private char symbol;


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
    public int count() {
        return 1;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
