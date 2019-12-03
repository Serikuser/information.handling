package by.siarhei.information.parser;

import by.siarhei.information.composite.ComponentType;
import by.siarhei.information.composite.impl.TextComposite;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InputTextParserTest {

    private String inputText;
    private InputTextToParagraphParser inputTextToParagraphParser;
    private ParagraphToSentenceParser paragraphToSentenceParser;
    private SentenceToLexemParser sentenceTolexemParser;
    private LexemToSymbolParser lexemToSymbolParser;
    private TextComposite composedText;

    @BeforeClass
    private void setUp() {
        inputText = "   Paragraph1. Sentence1 word1 word2, word3.\r\n" +
                "   Paragraph2. Sentence 1 word1 word2. word3, word4. Sentence 2 word1 word2?";
        inputTextToParagraphParser = new InputTextToParagraphParser();
        paragraphToSentenceParser = new ParagraphToSentenceParser();
        sentenceTolexemParser = new SentenceToLexemParser();
        lexemToSymbolParser = new LexemToSymbolParser();
        composedText = new TextComposite(ComponentType.TEXT);
    }

    @AfterClass
    private void setDown() {
        inputText = null;
        inputTextToParagraphParser = null;
        paragraphToSentenceParser = null;
        sentenceTolexemParser = null;
        lexemToSymbolParser = null;
        composedText = null;
    }

    @Test
    private void parseTextToComponentsTest() {
        inputTextToParagraphParser.setNextParser(paragraphToSentenceParser);
        paragraphToSentenceParser.setNextParser(sentenceTolexemParser);
        sentenceTolexemParser.setNextParser(lexemToSymbolParser);
        inputTextToParagraphParser.fillComponent(composedText, inputText);
        String expectedText = inputText;
        String actualText = composedText.toString();
        Assert.assertEquals(actualText, expectedText);
    }
}
