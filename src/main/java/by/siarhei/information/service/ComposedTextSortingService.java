package by.siarhei.information.service;

import by.siarhei.information.composite.TextComponent;
import by.siarhei.information.interpreter.AbstractMathExpression;

public class ComposedTextSortingService {

    public void sortComposedTextByParagraph(TextComponent text) {
        text.getChildrenList().sort((o1, o2)
                -> ComponentCalculationService.componentsCounter(o2) - ComponentCalculationService.componentsCounter(o1));
    }
}
