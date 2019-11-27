package by.siarhei.information.service;

import by.siarhei.information.composite.api.TextComponent;

import java.util.Comparator;

public enum CompareType {

    BY_SENTENCE_COUNT((o1, o2) -> ComponentCalculationService.componentsCounter(o2) - ComponentCalculationService.componentsCounter(o1));

    Comparator<TextComponent> comparator;

    CompareType(Comparator<TextComponent> comparator) {
        this.comparator = comparator;
    }

    public Comparator getComparatorByType() {
        return comparator;
    }

}
