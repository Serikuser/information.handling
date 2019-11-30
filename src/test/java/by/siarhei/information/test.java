package by.siarhei.information;

import by.siarhei.information.composite.ComponentType;
import by.siarhei.information.composite.impl.TextComposite;
import by.siarhei.information.parser.InputTextToParagraphParser;
import by.siarhei.information.parser.ParagraphToSentenceParser;
import by.siarhei.information.parser.SentenceToLexemParser;
import by.siarhei.information.parser.LexemToSymbolParser;
import by.siarhei.information.reader.InputTextReader;
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
    private SentenceToLexemParser sentenceTolexemParser;
    private LexemToSymbolParser lexemToSymbolParser;
    private ComposedTextSortingService composedTextSortingService;
    private ComposedTextSearchService composedTextSearchService;

    @BeforeClass
    public void setUp() {
        reader = new InputTextReader();
        inputTextToParagraphParser = new InputTextToParagraphParser();
        paragraphToSentenceParser = new ParagraphToSentenceParser();
        sentenceTolexemParser = new SentenceToLexemParser();
        lexemToSymbolParser = new LexemToSymbolParser();
        composedTextSortingService = new ComposedTextSortingService();
        composedTextSearchService = new ComposedTextSearchService();

    }

    @Test
    public void test() throws IOException {
        inputTextToParagraphParser.setNextParser(paragraphToSentenceParser);
        paragraphToSentenceParser.setNextParser(sentenceTolexemParser);
        sentenceTolexemParser.setNextParser(lexemToSymbolParser);
        TextComposite test = new TextComposite(ComponentType.TEXT);
        inputTextToParagraphParser.fillComponent(test, reader.readData());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/test.txt"));
        composedTextSearchService.findSentencesWithLongestWord(test);
        composedTextSortingService.sortComposedTextByParagraph(test);
        //composedTextSearchService.removeSentences(test,10);
        composedTextSearchService.wordMatchCount(test);
        logger.info(test);
        composedTextSearchService.wordMatchCount(test).entrySet().forEach(entry -> {
            logger.info(entry.getKey() + " " + entry.getValue());
        });
        writer.write(test.toString());
        writer.close();
    }
    @Test
    public void parseTest(){
        String [] tokens = "43&11".replaceAll("\\D"," ").trim().split(" ");
        String token = "43&11".replaceAll("\\D"," ");
        String token2 = "43&11".replaceAll("\\d"," ");
        logger.info(token);
        logger.info(token2);
    }
}
