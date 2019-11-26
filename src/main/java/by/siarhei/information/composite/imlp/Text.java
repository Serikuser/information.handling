package by.siarhei.information.composite.imlp;

import by.siarhei.information.composite.api.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class Text implements TextComponent {
    List<TextComponent> composedText = new ArrayList<>();

    @Override
    public int count() {
        return 0;
    }
}
