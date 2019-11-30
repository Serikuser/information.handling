package by.siarhei.information.service;

import by.siarhei.information.composite.ComponentType;
import by.siarhei.information.composite.TextComponent;
import by.siarhei.information.composite.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ComposedTextSearchService {
    private static final Logger logger = LogManager.getLogger();

    private static final String REGEX_NON_LETTERS = "[^a-zA-Z-]";

    public TextComponent findLongestWord(TextComponent text) {
        TextComponent longestWord = new TextComposite(ComponentType.LEXEM);
        for (TextComponent paragraph : text.getUnmodifiedComponentList()) {
            for (TextComponent sentence : paragraph.getUnmodifiedComponentList()) {
                for (TextComponent lexem : sentence.getUnmodifiedComponentList()) {
                    if (removeNonLetters(lexem).length()
                            > removeNonLetters(longestWord).length()) {
                        longestWord = lexem;

                    }
                }
            }
        }
        logger.info(String.format("Longest word is: %s", longestWord.toString()));
        return longestWord;
    }

    private String removeNonLetters(TextComponent textComponent) {
        return textComponent.toString().replaceAll(REGEX_NON_LETTERS, "");
    }

    public Set<TextComponent> findSentencesWithLongestWord(TextComponent text) {
        int longestWordLettersCount = removeNonLetters(findLongestWord(text)).length();
        Set<TextComponent> sentences = new HashSet<>();
        for (TextComponent paragraph : text.getUnmodifiedComponentList()) {
            for (TextComponent sentence : paragraph.getUnmodifiedComponentList()) {
                for (TextComponent lexem : sentence.getUnmodifiedComponentList()) {
                    if (removeNonLetters(lexem).length() == longestWordLettersCount) {
                        sentences.add(sentence);
                    }
                }
            }
        }
        logger.info(String.format("Sentences with longest word: %s", sentences));
        return sentences;
    }

    public void removeSentences(TextComponent text, int count) {
        for (TextComponent paragraph : text.getUnmodifiedComponentList()) {

            Iterator iterator = paragraph.getChildrenList().iterator();
            while (iterator.hasNext()) {
                TextComponent sentence = (TextComposite) iterator.next();
                if (ComponentCalculationService.componentsCounter(sentence) <= count) {
                    iterator.remove();
                }
            }
        }
    }

    public Map<String, Integer> wordMatchCount(TextComponent text) {
        Map<String, Integer> matches = new HashMap<>();
        for (TextComponent paragraph : text.getUnmodifiedComponentList()) {
            for (TextComponent sentence : paragraph.getUnmodifiedComponentList()) {
                for (TextComponent lexem : sentence.getUnmodifiedComponentList()) {
                    if (!removeNonLetters(lexem).isBlank()) {
                        if (matches.containsKey(removeNonLetters(lexem).toLowerCase())) {
                            int count = matches.get(removeNonLetters(lexem).toLowerCase());
                            matches.put(removeNonLetters(lexem).toLowerCase(), count + 1);
                        } else {
                            matches.put(removeNonLetters(lexem).toLowerCase(), 1);
                        }
                    }
                }
            }
        }
        return matches;
    }
}
