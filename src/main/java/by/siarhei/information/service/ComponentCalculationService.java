package by.siarhei.information.service;

import by.siarhei.information.composite.TextComponent;

import java.util.List;

public class ComponentCalculationService {
    private ComponentCalculationService() {
    }

    public static int componentsCounter(TextComponent text) {
        int counter = 0;
        for (TextComponent component : text.getUnmodifiedComponentList()) {
            counter += component.count();
        }
        return counter;
    }

    public static int componentsCounter(List<TextComponent> list) {
        int counter = 0;
        for (TextComponent component : list) {
            counter += component.count();
        }
        return counter;
    }
}
