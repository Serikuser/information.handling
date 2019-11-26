package by.siarhei.information.parser;

import by.siarhei.information.parser.api.AbstractParser;

import java.util.Arrays;
import java.util.List;

public class ParseTokenToWord extends AbstractParser {
    private static final String REGEX_WORD = "[^A-Za-z]";

    public List<String> parse(String sentence) {
        return Arrays.asList(sentence.split(REGEX_WORD));
    }
}
