package by.siarhei.information.service;

import by.siarhei.information.composite.ComponentType;
import by.siarhei.information.composite.impl.TextComposite;
import by.siarhei.information.parser.InputTextToParagraphParser;
import by.siarhei.information.parser.LexemToSymbolParser;
import by.siarhei.information.parser.ParagraphToSentenceParser;
import by.siarhei.information.parser.SentenceToLexemParser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ComposedTextSortingServiceTest {
    private static final String SORTED_TEXT = "   Paragraph3. Sentence2. Sentence3? Sentence3!\r\n" +
            "   Paragraph2. Sentence2. Sentence3?\r\n" +
            "   Paragraph1. Sentence2.";
    private String inputText;

    private TextComposite composedText;
    private InputTextToParagraphParser inputTextToParagraphParser;
    private ParagraphToSentenceParser paragraphToSentenceParser;
    private SentenceToLexemParser sentenceTolexemParser;
    private LexemToSymbolParser lexemToSymbolParser;
    private ComposedTextSortingService composedTextSortingService;

    @BeforeClass
    public void setUp() {
        inputText = "   Paragraph1. Sentence2.\r\n" +
                "   Paragraph2. Sentence2. Sentence3?\r\n" +
                "   Paragraph3. Sentence2. Sentence3? Sentence3!";
        inputTextToParagraphParser = new InputTextToParagraphParser();
        paragraphToSentenceParser = new ParagraphToSentenceParser();
        sentenceTolexemParser = new SentenceToLexemParser();
        lexemToSymbolParser = new LexemToSymbolParser();
        composedText = new TextComposite(ComponentType.TEXT);
        inputTextToParagraphParser.setNextParser(paragraphToSentenceParser);
        paragraphToSentenceParser.setNextParser(sentenceTolexemParser);
        sentenceTolexemParser.setNextParser(lexemToSymbolParser);
        inputTextToParagraphParser.fillComponent(composedText, inputText);
        composedTextSortingService = new ComposedTextSortingService();
    }

    @AfterClass
    public void setDown() {
        inputText = null;
        inputTextToParagraphParser = null;
        paragraphToSentenceParser = null;
        sentenceTolexemParser = null;
        lexemToSymbolParser = null;
        composedText = null;
    }

    @Test
    public void sortTextTest() {
        composedTextSortingService.sortComposedTextByParagraph(composedText);
        String actual = composedText.toString();
        Assert.assertEquals(actual, SORTED_TEXT);
    }
}
