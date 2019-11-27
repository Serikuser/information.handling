package by.siarhei.information.service;

import by.siarhei.information.composite.api.TextComponent;
import by.siarhei.information.composite.impl.ComposedText;
import by.siarhei.information.composite.impl.ComposedToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ComposedTextSearchService {
    private static final Logger logger = LogManager.getLogger();

    private ComposedTextSearchService() {
    }

    private static final String REGEX_NON_LETTERS = "[^a-zA-Z ]";

    public static TextComponent findLongestWord(ComposedText text) {
        TextComponent longestWord = new ComposedToken();
        for (TextComponent paragraph : text.getUnmodifiedComponentList()) {
            for (TextComponent sentence : paragraph.getUnmodifiedComponentList()) {
                for (TextComponent token : sentence.getUnmodifiedComponentList()) {
                    if (removeNonLetters(token).length()
                            > removeNonLetters(longestWord).length()) {
                        longestWord = token;

                    }
                }
            }
        }
        logger.info(String.format("Longest word is: %s", longestWord.toString()));
        return longestWord;
    }

    private static String removeNonLetters(TextComponent textComponent) {
        return textComponent.toString().replaceAll(REGEX_NON_LETTERS, "");
    }

    public static List<TextComponent> findSentencesWithLongestWord(ComposedText text) {
        int longestWordLettersCount = findLongestWord(text).toString().length();
        List<TextComponent> sentences = new ArrayList<>();
        for (TextComponent paragraph : text.getUnmodifiedComponentList()) {
            for (TextComponent sentence : paragraph.getUnmodifiedComponentList()) {
                for (TextComponent token : sentence.getUnmodifiedComponentList()) {
                    if (removeNonLetters(token).length() == longestWordLettersCount) {
                        sentences.add(sentence);
                    }
                }
            }
        }
        logger.info(String.format("Sentences with longest word: %s", sentences));
        return sentences;
    }
}
