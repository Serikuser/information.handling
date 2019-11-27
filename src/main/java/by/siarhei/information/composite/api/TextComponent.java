package by.siarhei.information.composite.api;

import java.util.List;

public interface TextComponent {
    int count();

    boolean addChild(TextComponent component);

    List<TextComponent> getUnmodifiedComponentList();
}
