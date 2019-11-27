package by.siarhei.information.parser;

import by.siarhei.information.composite.api.TextComponent;


public abstract class AbstractParser {
    private AbstractParser nextParser;

    public abstract void fillComponent(TextComponent textComponent, String text);

    protected boolean hasNext() {
        return !(this.nextParser == null);

    }

    public void setNextParser(AbstractParser parser) {
        nextParser = parser;
    }

    protected AbstractParser getNextParser() {
        return nextParser;
    }
}