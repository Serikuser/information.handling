package by.siarhei.information.parser;

import by.siarhei.information.parser.api.AbstractParser;

import java.util.Arrays;
import java.util.List;

public class ParseTokenToSymbol extends AbstractParser {
    private static final String REGEX_SYMBOL = "[A-Za-z]";

    public List<String> parse(String sentence) {
        return Arrays.asList(sentence.split(REGEX_SYMBOL));
    }
}
