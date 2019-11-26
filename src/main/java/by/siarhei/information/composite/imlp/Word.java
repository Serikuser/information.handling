package by.siarhei.information.composite.imlp;

import by.siarhei.information.composite.api.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class Word implements TextComponent {
    List<TextComponent> composedWord = new ArrayList<>();

    @Override
    public int count() {
        return 1;
    }
}
