package by.siarhei.information.composite.impl;

import by.siarhei.information.composite.ComponentType;
import by.siarhei.information.composite.TextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComposite implements TextComponent {

    private static final String PARAGRAPH_TAB = "  ";
    private static final int FIRST_PARAGRAPH_INDEX = 0;

    private List<TextComponent> childrenList = new ArrayList<>();
    private ComponentType type;

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public List<TextComponent> getChildrenList() {
        return childrenList;
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
    public boolean addChild(TextComponent component) {
        return childrenList.add(component);
    }

    @Override
    public List<TextComponent> getUnmodifiedComponentList() {
        return Collections.unmodifiableList(childrenList);
    }

    @Override
    public void removeChild(TextComponent sentence) {
        childrenList.remove(sentence);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (TextComponent children : childrenList) {
            if (children.getComponentType().equals(ComponentType.PARAGRAPH)) {
                if (children != childrenList.get(FIRST_PARAGRAPH_INDEX) && text.length() != 0) {
                    text.append("\n");
                    text.append(PARAGRAPH_TAB);
                } else {
                    text.append(PARAGRAPH_TAB);
                }
            }
            if (children.getComponentType().equals(ComponentType.LEXEM)) {
                text.append(" ");
            }
            text.append(children.toString());
        }
        return text.toString();
    }
}
