package ru.mail.gasimov.task4.parser;

import ru.mail.gasimov.task4.entity.TextComponent;
import ru.mail.gasimov.task4.entity.TextComponentType;
import ru.mail.gasimov.task4.entity.TextComposite;
import ru.mail.gasimov.task4.exception.InformationHandlerException;

public class SentenceParser extends AbstractParser {
    private static final String SENTENCE_SPLIT_REGEX = "(?<=\\.{3}|\\.|\\?|!)\\s";


    @Override
    public void parse(TextComponent component, String data) throws InformationHandlerException {
        String[] sentences = data.split(SENTENCE_SPLIT_REGEX);

        for (String sentence : sentences) {
            TextComposite sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            component.add(sentenceComponent);

            nextParser.parse(sentenceComponent, sentence);
        }
    }
}
