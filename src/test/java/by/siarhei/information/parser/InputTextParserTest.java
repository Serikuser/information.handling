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
    private TextComposite composedTextWithExpressions;
    private String inputTextWithExpressions;
    private String expectedOutputWithExpressions;

    @BeforeClass
    public void setUp() {
        inputText = "   Paragraph1. Sentence1 word1 word2, word3.\r\n" +
                "   Paragraph2. Sentence 1 word1 word2. word3, word4. Sentence 2 word1 word2?";

        inputTextWithExpressions =
                "   It has survived - not only (five) centuries, but also the leap into 13<<2 electronic\r\n" +
                " typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in\r\n" +
                " “Динамо” (Рига) the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets\r\n" +
                " containing Lorem Ipsum passages, and more recently with desktop publishing software\r\n" +
                " like Aldus Faclon9 PageMaker including versions of Lorem Ipsum.\r\n" +
                "   It is a long a!=b established fact that a reader, will be distracted by the readable\r\n" +
                " content of a page when looking at its layout. The point of using\r\n" +
                " (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal\r\n" +
                " distribution ob.toString(a?b:c) of letters, as opposed to using (Content here), content here', making it look\r\n" +
                " like readable English.\r\n" +
                "   It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a\r\n" +
                " page when looking at its layout.\r\n" +
                "   Bye. Бандерлоги.";

        expectedOutputWithExpressions =
                "   It has survived - not only (five) centuries, but also the leap into 52 electronic\r\n" +
                " typesetting, remaining 0 essentially 9 unchanged. It was popularised in\r\n" +
                " “Динамо” (Рига) the 5 with the release of Letraset sheets\r\n" +
                " containing Lorem Ipsum passages, and more recently with desktop publishing software\r\n" +
                " like Aldus Faclon9 PageMaker including versions of Lorem Ipsum.\r\n" +
                "   It is a long a!=b established fact that a reader, will be distracted by the readable\r\n" +
                " content of a page when looking at its layout. The point of using\r\n" +
                " 78 Ipsum is that it has a more-or-less normal\r\n" +
                " distribution ob.toString(a?b:c) of letters, as opposed to using (Content here), content here', making it look\r\n" +
                " like readable English.\r\n" +
                "   It is a 1202 established fact that a reader will be of a\r\n" +
                " page when looking at its layout.\r\n" +
                "   Bye. Бандерлоги.";

        inputTextToParagraphParser = new InputTextToParagraphParser();
        paragraphToSentenceParser = new ParagraphToSentenceParser();
        sentenceTolexemParser = new SentenceToLexemParser();
        lexemToSymbolParser = new LexemToSymbolParser();
        inputTextToParagraphParser.setNextParser(paragraphToSentenceParser);
        paragraphToSentenceParser.setNextParser(sentenceTolexemParser);
        sentenceTolexemParser.setNextParser(lexemToSymbolParser);
        composedText = new TextComposite(ComponentType.TEXT);
        composedTextWithExpressions = new TextComposite(ComponentType.TEXT);
    }

    @AfterClass
    public void setDown() {
        inputText = null;
        inputTextToParagraphParser = null;
        paragraphToSentenceParser = null;
        sentenceTolexemParser = null;
        lexemToSymbolParser = null;
        composedText = null;
        inputTextWithExpressions=null;
        expectedOutputWithExpressions = null;
        composedTextWithExpressions = null;
    }

    @Test
    public void parseTextToComponentsTest() {
        inputTextToParagraphParser.fillComponent(composedText, inputText);
        String expectedText = inputText;
        String actualText = composedText.toString();
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void parseTextWithExpressionsToComponentsTest() {
        inputTextToParagraphParser.fillComponent(composedTextWithExpressions, inputTextWithExpressions);
        String actualText = composedTextWithExpressions.toString();
        Assert.assertEquals(actualText, expectedOutputWithExpressions);
    }
}
