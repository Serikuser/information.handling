package by.siarhei.information.parser;

import by.siarhei.information.parser.api.AbstractParser;

import java.util.Arrays;
import java.util.List;

public class ParseSentenceToToken extends AbstractParser {

    private static final String REGEX_TOKEN = " ";

    public List<String> parse(String sentence) {
        return Arrays.asList(sentence.split(REGEX_TOKEN));
    }
}
