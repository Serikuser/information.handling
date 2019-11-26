package by.siarhei.information.parser;

import by.siarhei.information.parser.api.AbstractParser;

import java.util.Arrays;
import java.util.List;

public class ParseInputTextToParagraph extends AbstractParser {

    private static final String REGEX_PARAGRAPH = "[ ]{2,}";

    public List<String> parse(String text) {
        String[] subLines = text.split(REGEX_PARAGRAPH);
        return (Arrays.asList(subLines));
    }
}
