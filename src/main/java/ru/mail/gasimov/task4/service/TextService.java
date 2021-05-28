package ru.mail.gasimov.task4.service;

import ru.mail.gasimov.task4.entity.TextComponent;
import ru.mail.gasimov.task4.exception.InformationHandlerException;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortParagraphsBySentenceAmount(TextComponent text) throws InformationHandlerException;

    List<TextComponent> findSentencesWithLongestWord(TextComponent text) throws InformationHandlerException;

    TextComponent removeSentenceWithWordCountLessThan(TextComponent text, int wordCount)
            throws InformationHandlerException;

    Map<String, Integer> countWordsOccurrences(TextComponent text) throws InformationHandlerException;

    long countVowels(TextComponent sentence) throws InformationHandlerException;

    long countConsonants(TextComponent sentence) throws InformationHandlerException;
}
