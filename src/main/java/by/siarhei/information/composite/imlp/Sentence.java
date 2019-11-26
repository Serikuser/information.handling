package by.siarhei.information.composite.imlp;

import by.siarhei.information.composite.api.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class Sentence implements TextComponent {
    List<TextComponent> composedSentence = new ArrayList<>();

    @Override
    public int count() {
        return 1;
    }
}
