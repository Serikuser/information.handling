package by.siarhei.information.service;

import by.siarhei.information.composite.ComponentType;
import by.siarhei.information.composite.TextComponent;
import by.siarhei.information.composite.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ComposedTextSearchService {
    private static final Logger logger = LogManager.getLogger();

    private static final String REGEX_NON_WORDS = "[^0-9a-zA-Zа-яА-Я-]";

    public TextComponent findLongestWord(TextComponent text) {
        TextComponent longestWord = new TextComposite(ComponentType.LEXEM);
        for (TextComponent paragraph : text.getChildrenList()) {
            for (TextComponent sentence : paragraph.getChildrenList()) {
                for (TextComponent lexem : sentence.getChildrenList()) {
                    if (removeNonWordsParts(lexem).length()
                            > removeNonWordsParts(longestWord).length()) {
                        longestWord = lexem;

                    }
                }
            }
        }
        logger.info(String.format("Longest word is: %s", longestWord.toString()));
        return longestWord;
    }

    public List<TextComponent> findSentencesWithLongestWord(TextComponent text) {
        int longestWordLettersCount = removeNonWordsParts(findLongestWord(text)).length();
        List<TextComponent> sentences = new LinkedList<>();
        for (TextComponent paragraph : text.getChildrenList()) {
            for (TextComponent sentence : paragraph.getChildrenList()) {
                for (TextComponent lexem : sentence.getChildrenList()) {
                    if (removeNonWordsParts(lexem).length() == longestWordLettersCount) {
                        sentences.add(sentence);
                    }
                }
            }
        }
        logger.info(String.format("Sentences with longest word: %s", sentences));
        return sentences;
    }

    public void removeSentences(TextComponent text, int count) {
        for (TextComponent paragraph : text.getChildrenList()) {
            Iterator iterator = paragraph.getChildrenList().iterator();
            while (iterator.hasNext()) {
                TextComponent sentence = (TextComposite) iterator.next();
                if (ComponentCalculationService.componentsCounter(sentence) <= count) {
                    iterator.remove();
                }
            }
        }
    }

    public int wordMatchCount(TextComponent text) {
        Map<String, Integer> matchesMap = new HashMap<>();
        for (TextComponent paragraph : text.getChildrenList()) {
            for (TextComponent sentence : paragraph.getChildrenList()) {
                for (TextComponent lexem : sentence.getChildrenList()) {
                    if (!removeNonWordsParts(lexem).isBlank()) {
                        putValue(lexem, matchesMap);
                    }
                }
            }
        }
        return countMatches(matchesMap);
    }

    private String removeNonWordsParts(TextComponent textComponent) {
        return textComponent.toString().replaceAll(REGEX_NON_WORDS, "");
    }

    private int countMatches(Map<String, Integer> matchesMap) {
        int matches = 0;
        for (int number : matchesMap.values()) {
            if (number > 1) {
                matches += number;
            }
        }
        logger.info(String.format("Total matches count: %s", matches));
        return matches;
    }

    private void increaseValue(TextComponent lexem, Map<String, Integer> matchesMap) {
        int count = matchesMap.get(removeNonWordsParts(lexem).toLowerCase());
        matchesMap.put(removeNonWordsParts(lexem).toLowerCase(), count + 1);
    }

    private void putValue(TextComponent lexem, Map<String, Integer> matchesMap) {
        if (matchesMap.containsKey(removeNonWordsParts(lexem).toLowerCase())) {
            increaseValue(lexem, matchesMap);
        } else {
            matchesMap.put(removeNonWordsParts(lexem).toLowerCase(), 1);
        }
    }
}

