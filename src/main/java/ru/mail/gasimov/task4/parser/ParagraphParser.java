package ru.mail.gasimov.task4.parser;

import ru.mail.gasimov.task4.entity.TextComponent;
import ru.mail.gasimov.task4.entity.TextComponentType;
import ru.mail.gasimov.task4.entity.TextComposite;
import ru.mail.gasimov.task4.exception.InformationHandlerException;

public class ParagraphParser extends AbstractParser {
    private static final String PARAGRAPH_SPLIT_REGEX = "\\s\\s\\s\\s";

    @Override
    public void parse(TextComponent component, String data) throws InformationHandlerException {
        String[] paragraphs = data.split(PARAGRAPH_SPLIT_REGEX);

        for (String paragraph : paragraphs) {
            TextComposite paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            component.add(paragraphComponent);

            nextParser.parse(paragraphComponent, paragraph);
        }
    }
}
