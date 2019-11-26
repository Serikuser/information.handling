package by.siarhei.information;

import by.siarhei.information.composite.impl.ComposedText;
import by.siarhei.information.parser.ParseInputTextToParagraph;
import by.siarhei.information.parser.ParseParagraphToSentence;
import by.siarhei.information.parser.ParseSentenceToToken;
import by.siarhei.information.parser.ParseTokenToSymbol;
import by.siarhei.information.reader.InputTextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class test {
    private static final Logger logger = LogManager.getLogger();

    private InputTextReader reader;
    private ParseInputTextToParagraph parseInputTextToParagraph;
    private ParseParagraphToSentence parseParagraphToSentence;
    private ParseSentenceToToken parseSentenceToToken;
    private ParseTokenToSymbol parseTokenToSymbol;

    @BeforeClass
    public void setUp() {
        reader = new InputTextReader();
        parseInputTextToParagraph = new ParseInputTextToParagraph();
        parseParagraphToSentence = new ParseParagraphToSentence();
        parseSentenceToToken = new ParseSentenceToToken();
        parseTokenToSymbol = new ParseTokenToSymbol();

    }

    @Test
    public void test() throws IOException {
        parseInputTextToParagraph.setNextParser(parseParagraphToSentence);
        parseParagraphToSentence.setNextParser(parseSentenceToToken);
        parseSentenceToToken.setNextParser(parseTokenToSymbol);
        ComposedText test = new ComposedText();
        parseInputTextToParagraph.fillComponent(test, reader.readData());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/test.txt"));
        writer.write(test.toString());
        writer.close();
    }
}
