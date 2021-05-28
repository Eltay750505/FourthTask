package ru.mail.gasimov.task4.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.gasimov.task4.entity.TextComponent;
import ru.mail.gasimov.task4.entity.TextComponentType;
import ru.mail.gasimov.task4.entity.TextComposite;
import ru.mail.gasimov.task4.exception.InformationHandlerException;
import ru.mail.gasimov.task4.service.TextService;

import java.util.*;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String VOWEL_REGEX = "[aAeEiIoOuU]";


    @Override
    public List<TextComponent> sortParagraphsBySentenceAmount(TextComponent text) throws InformationHandlerException {
        if (!text.getTextComponentType().equals(TextComponentType.TEXT)) {
            throw new InformationHandlerException("Expected component of text type but got: " + text.getTextComponentType());
        }

        List<TextComponent> sortedParagraphs = text.getChildrenAsCollection()
                .stream()
                .sorted(new ParagraphComparator())
                .collect(Collectors.toList());

        return sortedParagraphs;
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComponent text) throws InformationHandlerException {
        if (!text.getTextComponentType().equals(TextComponentType.TEXT)) {
            throw new InformationHandlerException("Expected component of text type but got: " + text.getTextComponentType());
        }

        List<TextComponent> allSentences = text.getChildrenAsCollection()
                .stream()
                .flatMap(paragraph -> paragraph.getChildrenAsCollection().stream())
                .collect(Collectors.toList());

        int maxLength = allSentences.stream()
                .map(this::computeMaxWordLength)
                .max(Integer::compareTo)
                .orElseThrow(() -> new InformationHandlerException("Sentence contains no words"));

        List<TextComponent> sentencesWithLongestWord = allSentences.stream()
                .filter(sentence -> computeMaxWordLength(sentence) == maxLength)
                .collect(Collectors.toList());

        return sentencesWithLongestWord;
    }

    @Override
    public TextComponent removeSentenceWithWordCountLessThan(TextComponent text, int wordCount) throws InformationHandlerException {
        if (!text.getTextComponentType().equals(TextComponentType.TEXT)) {
            throw new InformationHandlerException("Expected component of text type but got: " + text.getTextComponentType());
        }

        TextComponent newText = new TextComposite(TextComponentType.TEXT);

        text.getChildrenAsCollection().forEach(paragraph -> {
            Collection<TextComponent> newSentences = paragraph.getChildrenAsCollection();
            newSentences.removeIf(sentence -> sentence.getChildrenAsCollection().stream().filter(component ->
                    matchesType(component, TextComponentType.WORD)).count() < wordCount);

            TextComponent newParagraph = new TextComposite(TextComponentType.PARAGRAPH);
            newParagraph.addAll(newSentences);

            newText.add(newParagraph);
        });

        return newText;
    }

    @Override
    public Map<String, Integer> countWordsOccurrences(TextComponent text) throws InformationHandlerException {
        if (!text.getTextComponentType().equals(TextComponentType.TEXT)) {
            throw new InformationHandlerException("Expected component of text type but got: " + text.getTextComponentType());
        }

        Map<String, Integer> map = new HashMap<>();
        text.getChildrenAsCollection()
                .stream()
                .flatMap(component -> component.getChildrenAsCollection().stream())
                .flatMap(component -> component.getChildrenAsCollection().stream())
                .filter(component -> matchesType(component, TextComponentType.WORD))
                .forEach(word -> {
                    String key = word.toString();
                    int occurrences = map.getOrDefault(key, 0);
                    map.put(key, ++occurrences);
                });

        return map;
    }

    @Override
    public long countVowels(TextComponent sentence) throws InformationHandlerException {
        if (!sentence.getTextComponentType().equals(TextComponentType.SENTENCE)) {
            throw new InformationHandlerException("Expected component of sentence type but got: " + sentence.getTextComponentType());
        }

        long vowelsCount = sentence.getChildrenAsCollection()
                .stream()
                .filter(component -> matchesType(component, TextComponentType.WORD))
                .flatMap(component -> component.getChildrenAsCollection().stream())
                .filter(letter -> Pattern.matches(VOWEL_REGEX, letter.toString()))
                .count();

        return vowelsCount;
    }

    @Override
    public long countConsonants(TextComponent sentence) throws InformationHandlerException {
        if (!sentence.getTextComponentType().equals(TextComponentType.SENTENCE)) {
            throw new InformationHandlerException("Expected component of sentence type but got: " + sentence.getTextComponentType());
        }

        long consonantsCount = sentence.getChildrenAsCollection()
                .stream() // stream of lexemes
                .filter(component -> matchesType(component, TextComponentType.WORD))
                .flatMap(component -> component.getChildrenAsCollection().stream())
                .filter(letter -> !Pattern.matches(VOWEL_REGEX, letter.toString()))
                .count();

        return consonantsCount;
    }

    private static class ParagraphComparator implements Comparator<TextComponent> {
        @Override
        public int compare(TextComponent first, TextComponent second) {
            long firstCount = first.getChildrenAsCollection().size();
            long secondCount = second.getChildrenAsCollection().size();
            return Long.compare(firstCount, secondCount);
        }
    }

    private int computeMaxWordLength(TextComponent sentence) {
        int maxLength = sentence.getChildrenAsCollection()
                .stream() // stream of lexemes
                .filter(lexeme -> matchesType(lexeme, TextComponentType.WORD)) // stream of words
                .map(word -> word.getChildrenAsCollection().size())
                .max(Integer::compareTo)
                .orElse(0);

        return maxLength;
    }

    private boolean matchesType(TextComponent component, TextComponentType type) {
        return component.getTextComponentType().equals(type);
    }
}
