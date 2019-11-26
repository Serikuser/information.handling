package by.siarhei.information.parser;

import by.siarhei.information.parser.api.AbstractParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseParagraphToSentence extends AbstractParser {

    private static final String REGEX_SENTENCE = "(?<=[a-z])\\.\\s+";

    public List<String> parse(String paragraph) {
        List<String> sentences = new ArrayList<>();
        sentences.addAll(Arrays.asList(paragraph.split(REGEX_SENTENCE)));
        return sentences;
    }
}
