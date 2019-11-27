package by.siarhei.information.composite.api;

import java.util.List;

public interface TextComponent {
    int count();

    boolean addChild(TextComponent component);

    List<TextComponent> getUnmodifiedComponentList();

    void removeChild(TextComponent component);

    List<TextComponent> getChildrenList();
}
