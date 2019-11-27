package by.siarhei.information;

import by.siarhei.information.composite.impl.ComposedText;
import by.siarhei.information.parser.InputTextToParagraphParser;
import by.siarhei.information.parser.ParagraphToSentenceParser;
import by.siarhei.information.parser.SentenceToTokenParser;
import by.siarhei.information.parser.TokenToSymbolParser;
import by.siarhei.information.reader.InputTextReader;
import by.siarhei.information.service.CompareType;
import by.siarhei.information.service.ComponentCalculationService;
import by.siarhei.information.service.ComposedTextSearchService;
import by.siarhei.information.service.ComposedTextSortingService;
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
    private InputTextToParagraphParser inputTextToParagraphParser;
    private ParagraphToSentenceParser paragraphToSentenceParser;
    private SentenceToTokenParser sentenceToTokenParser;
    private TokenToSymbolParser tokenToSymbolParser;
    private ComposedTextSortingService composedTextSortingService;

    @BeforeClass
    public void setUp() {
        reader = new InputTextReader();
        inputTextToParagraphParser = new InputTextToParagraphParser();
        paragraphToSentenceParser = new ParagraphToSentenceParser();
        sentenceToTokenParser = new SentenceToTokenParser();
        tokenToSymbolParser = new TokenToSymbolParser();
        composedTextSortingService = new ComposedTextSortingService();

    }

    @Test
    public void test() throws IOException {
        inputTextToParagraphParser.setNextParser(paragraphToSentenceParser);
        paragraphToSentenceParser.setNextParser(sentenceToTokenParser);
        sentenceToTokenParser.setNextParser(tokenToSymbolParser);
        ComposedText test = new ComposedText();
        inputTextToParagraphParser.fillComponent(test, reader.readData());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/test.txt"));
        writer.write(test.toString());
        writer.close();
        ComposedTextSearchService.findLongestWord(test);
        ComposedTextSearchService.findSentencesWithLongestWord(test);
        logger.info(composedTextSortingService.sortComposedText(test, CompareType.BY_SENTENCE_COUNT));

    }
}
