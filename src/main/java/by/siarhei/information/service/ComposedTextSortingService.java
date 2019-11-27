package by.siarhei.information.service;

import by.siarhei.information.composite.impl.ComposedText;

public class ComposedTextSortingService {

    public void sortComposedText(ComposedText text, CompareType type) {
        text.getChildrenList().sort(type.getComparatorByType());
    }
}
