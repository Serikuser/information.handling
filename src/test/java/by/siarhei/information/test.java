package by.siarhei.information;

import by.siarhei.information.parser.*;
import by.siarhei.information.reader.InputTextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class test {
    private static final Logger logger = LogManager.getLogger();

    private InputTextReader reader;
    private ParseInputTextToParagraph parseInputTextToParagraph;
    private ParseParagraphToSentence parseParagraphToSentence;
    private ParseSentenceToToken parseSentenceToToken;
    private ParseTokenToWord parseTokenToWord;
    private ParseTokenToSymbol parseTokenToSymbol;
    private ParseTokenToBitExpression parseTokenToBitExpression;
    private ParseWordToLetters parseWordToLetters;

    @BeforeClass
    public void setUp() {
        reader = new InputTextReader();
        parseInputTextToParagraph = new ParseInputTextToParagraph();
        parseParagraphToSentence = new ParseParagraphToSentence();
        parseSentenceToToken = new ParseSentenceToToken();
        parseTokenToWord = new ParseTokenToWord();
        parseTokenToSymbol = new ParseTokenToSymbol();
        parseTokenToBitExpression = new ParseTokenToBitExpression();
        parseWordToLetters = new ParseWordToLetters();
    }

    @Test
    public void test() {
        String text = reader.readData();
        List<String> paragraphs = parseInputTextToParagraph.parse(text);

        List<String> sentences =  new ArrayList<>();
        for(String paragraph : paragraphs){
            sentences.addAll(parseParagraphToSentence.parse(paragraph));
        }

        List<String> tokens = new ArrayList<>();
        for(String sentence : sentences){
            tokens.addAll(parseSentenceToToken.parse(sentence));
        }

        List<String> words = new ArrayList<>();
        for(String token : tokens){
            words.addAll(parseTokenToWord.parse(token));

        }

        List<String> symbols = new ArrayList<>();
        for(String token : tokens){
            symbols.addAll(parseTokenToSymbol.parse(token));
        }

        List<Character> letters = new ArrayList<>();
        for(String word : words){
            letters.addAll(parseWordToLetters.parse(word));
        }
    }
}
