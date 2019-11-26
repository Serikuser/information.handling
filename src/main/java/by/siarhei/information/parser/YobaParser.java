package by.siarhei.information.parser;

import by.siarhei.information.composite.api.TextComponent;
import by.siarhei.information.composite.imlp.Text;

import java.util.List;

public class YobaParser {
    public TextComponent parse(String text){
        Text composedText = new Text();
        ParseInputTextToParagraph parseInputTextToParagraph =  new ParseInputTextToParagraph();
        List<String> poragraphs = parseInputTextToParagraph.parse(text);
        return composedText;
    }
}
