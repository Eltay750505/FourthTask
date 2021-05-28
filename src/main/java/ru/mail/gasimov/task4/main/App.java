package ru.mail.gasimov.task4.main;

import ru.mail.gasimov.task4.entity.TextComponent;
import ru.mail.gasimov.task4.entity.TextComponentType;
import ru.mail.gasimov.task4.entity.TextComposite;
import ru.mail.gasimov.task4.exception.InformationHandlerException;
import ru.mail.gasimov.task4.parser.AbstractParser;
import ru.mail.gasimov.task4.parser.ParserFactory;
import ru.mail.gasimov.task4.reader.InformationHandlerReader;
import ru.mail.gasimov.task4.service.TextService;
import ru.mail.gasimov.task4.service.impl.TextServiceImpl;

import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws InformationHandlerException {
        String filePath = "src\\main\\resources\\data\\data.txt";
        InformationHandlerReader reader = new InformationHandlerReader();
        String text = reader.readFromFile(filePath);

        AbstractParser parser = ParserFactory.createParser();
        TextComposite composite = new TextComposite(TextComponentType.TEXT);
        parser.parse(composite, text);

        TextService textService = new TextServiceImpl();

        List<TextComponent> sentencesWithLongestWord = textService.findSentencesWithLongestWord(composite);
        List<TextComponent> sortedBySentenceAmount = textService.sortParagraphsBySentenceAmount(composite);
        long countConsonants = textService.countConsonants(composite);
        long countVowels = textService.countVowels(composite);
        Map<String, Integer> stringIntegerMap = textService.countWordsOccurrences(composite);

        int wordCount = 5;

        TextComponent textComponent = textService.removeSentenceWithWordCountLessThan(composite, wordCount);


    }
}
