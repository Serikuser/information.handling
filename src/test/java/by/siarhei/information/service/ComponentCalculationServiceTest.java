package by.siarhei.information.service;

import by.siarhei.information.composite.ComponentType;
import by.siarhei.information.composite.TextComponent;
import by.siarhei.information.composite.impl.TextComposite;
import by.siarhei.information.parser.InputTextToParagraphParser;
import by.siarhei.information.parser.LexemToSymbolParser;
import by.siarhei.information.parser.ParagraphToSentenceParser;
import by.siarhei.information.parser.SentenceToLexemParser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ComponentCalculationServiceTest {

    private static final int ACTUAL_PARAGRAPH_NUMBER = 3;
    private static final int WRONG_PARAGRAPH_NUMBER = 22;
    private static final int ACTUAL_SENTENCE_NUMBER = 8;
    private static final int WRONG_SENTENCE_NUMBER = 22;

    private String inputText;
    private TextComposite composedText;
    private InputTextToParagraphParser inputTextToParagraphParser;
    private ParagraphToSentenceParser paragraphToSentenceParser;
    private SentenceToLexemParser sentenceTolexemParser;
    private LexemToSymbolParser lexemToSymbolParser;

    @BeforeClass
    public void setUp() {
        inputText = "   Paragraph1. Sentence2 word1 word2, word3.\r\n" +
                "   Paragraph2. Sentence 2 word1 word2, word3, word4. Sentence 3 word1 word2?" +
                "   Paragraph3. Sentence 2 word1 word2, word3, word4. Sentence 3 word1 word2?";
        inputTextToParagraphParser = new InputTextToParagraphParser();
        paragraphToSentenceParser = new ParagraphToSentenceParser();
        sentenceTolexemParser = new SentenceToLexemParser();
        lexemToSymbolParser = new LexemToSymbolParser();
        composedText = new TextComposite(ComponentType.TEXT);
        inputTextToParagraphParser.setNextParser(paragraphToSentenceParser);
        paragraphToSentenceParser.setNextParser(sentenceTolexemParser);
        sentenceTolexemParser.setNextParser(lexemToSymbolParser);
        inputTextToParagraphParser.fillComponent(composedText, inputText);
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
    public void calculateParagraphsTestPositive() {
        int actual = ComponentCalculationService.componentsCounter(composedText);
        Assert.assertEquals(actual, ACTUAL_PARAGRAPH_NUMBER);
    }

    @Test
    public void calculateParagraphsTestNegative() {
        int actual = ComponentCalculationService.componentsCounter(composedText);
        Assert.assertNotEquals(actual, WRONG_PARAGRAPH_NUMBER);
    }

    @Test
    public void calculateSentencesTestPositive() {
        List<TextComponent> paragraphs = composedText.getChildrenList();
        int actual = 0;
        for (TextComponent paragraph : paragraphs) {
            actual += ComponentCalculationService.componentsCounter(paragraph);
        }
        Assert.assertEquals(actual, ACTUAL_SENTENCE_NUMBER);
    }

    @Test
    public void calculateSentencesTestNegative() {
        List<TextComponent> paragraphs = composedText.getChildrenList();
        int actual = 0;
        for (TextComponent paragraph : paragraphs) {
            actual += ComponentCalculationService.componentsCounter(paragraph);
        }
        Assert.assertNotEquals(actual, WRONG_SENTENCE_NUMBER);
    }
}
