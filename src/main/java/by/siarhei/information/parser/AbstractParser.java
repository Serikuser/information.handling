package by.siarhei.information.parser;

import by.siarhei.information.composite.TextComponent;


public abstract class AbstractParser {

    public void removeNextParser() {
        nextParser = null;
    }

    public abstract void fillComponent(TextComponent textComponent, String text);

    public void setNextParser(AbstractParser parser) {
        nextParser = parser;
    }

    protected AbstractParser getNextParser() {
        return nextParser;
    }

    protected boolean hasNext() {
        return this.nextParser != null;
    }

    private AbstractParser nextParser;

}