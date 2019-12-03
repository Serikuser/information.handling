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

import java.util.Map;

public class ComposedTextSearchServiceTest {

    private static final String LONGEST_WORD = "longestword";
    private static final String SHORTEST_WORD = "sw";
    private static final String SENTENCES_WITH_LONGEST_WORDS =
            " Sentence2 word1 word2, word3, longestword word4. Sentence2 word1 word2, word3, longestword word4.";
    private static final String SENTENCE_WITH_SHORTEST_WORD = "S w s w.";
    private static final String TEXT_AFTER_REMOVE = "   Sentence2 word1 word2, word3.\r\n" +
            "   Sentence2 word1 word2, word3, longestword word4. Sentence3 word1 word2?\r\n" +
            "   Sentence2 word1 word2, word3, longestword word4. Sentence3 word1 word2?";
    private static final int MATCH_COUNT = 22;

    private String inputText;
    private TextComposite composedText;
    private TextComposite composedTextToRemoveTest;
    private TextComposite composedTextToMatchTest;
    private InputTextToParagraphParser inputTextToParagraphParser;
    private ParagraphToSentenceParser paragraphToSentenceParser;
    private SentenceToLexemParser sentenceTolexemParser;
    private LexemToSymbolParser lexemToSymbolParser;
    private ComposedTextSearchService composedTextSearchService;

    @BeforeClass
    private void setUp() {
        inputText = "   Paragraph1. Sentence2 word1 word2, word3.\r\n" +
                "   Paragraph2. Sentence2 word1 word2, word3, longestword word4. Sentence3 word1 word2?" +
                "   Paragraph3. Sentence2 word1 word2, word3, longestword word4. Sentence3 word1 word2?";
        inputTextToParagraphParser = new InputTextToParagraphParser();
        paragraphToSentenceParser = new ParagraphToSentenceParser();
        sentenceTolexemParser = new SentenceToLexemParser();
        lexemToSymbolParser = new LexemToSymbolParser();
        composedText = new TextComposite(ComponentType.TEXT);
        inputTextToParagraphParser.setNextParser(paragraphToSentenceParser);
        paragraphToSentenceParser.setNextParser(sentenceTolexemParser);
        sentenceTolexemParser.setNextParser(lexemToSymbolParser);
        inputTextToParagraphParser.fillComponent(composedText, inputText);
        composedTextSearchService = new ComposedTextSearchService();
        composedTextToRemoveTest = composedText;
        composedTextToMatchTest = composedText;
    }

    @AfterClass
    private void setDown() {
        inputText = null;
        inputTextToParagraphParser = null;
        paragraphToSentenceParser = null;
        sentenceTolexemParser = null;
        lexemToSymbolParser = null;
        composedText = null;
        composedTextSearchService = null;
    }

    @Test
    private void findLongestWordTestPositive() {
        String actual = composedTextSearchService.findLongestWord(composedText).toString();
        Assert.assertEquals(actual, LONGEST_WORD);
    }

    @Test
    private void findLongestWordTestNegative() {
        String actual = composedTextSearchService.findLongestWord(composedText).toString();
        Assert.assertNotEquals(actual, SHORTEST_WORD);
    }

    @Test
    private void findSentencesWithLongestWordTestPositive() {
        StringBuilder actual = new StringBuilder();
        for (TextComponent sentence : composedTextSearchService.findSentencesWithLongestWord(composedText)) {
            actual.append(sentence.toString());
        }
        Assert.assertEquals(actual.toString(), SENTENCES_WITH_LONGEST_WORDS);
    }

    @Test
    private void findSentencesWithLongestWordTestNegative() {
        StringBuilder actual = new StringBuilder();
        for (TextComponent sentence : composedTextSearchService.findSentencesWithLongestWord(composedText)) {
            actual.append(sentence.toString());
        }
        Assert.assertNotEquals(actual.toString(), SENTENCE_WITH_SHORTEST_WORD);
    }

    @Test
    private void removeSentencesTest() {
        composedTextSearchService.removeSentences(composedTextToRemoveTest, 2);
        String actual = composedTextToRemoveTest.toString();
        Assert.assertEquals(actual, TEXT_AFTER_REMOVE);
    }

    @Test
    private void findMatchesTest() {
        Map<String, Integer> matchesMap = composedTextSearchService.wordMatchCount(composedTextToMatchTest);
        int actual = 0;
        for (int number : matchesMap.values()) {
            if (number > 1) {
                actual += number;
            }
        }
        Assert.assertEquals(actual, MATCH_COUNT);
    }
}
