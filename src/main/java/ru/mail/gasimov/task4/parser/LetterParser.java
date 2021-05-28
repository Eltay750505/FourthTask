package ru.mail.gasimov.task4.parser;

import ru.mail.gasimov.task4.entity.CustomSymbol;
import ru.mail.gasimov.task4.entity.TextComponent;
import ru.mail.gasimov.task4.entity.TextComponentType;
import ru.mail.gasimov.task4.exception.InformationHandlerException;

public class LetterParser extends AbstractParser {
    @Override
    public void parse(TextComponent component, String data) throws InformationHandlerException {
        char[] symbols = data.toCharArray();

        for (char symbolValue : symbols) {
            CustomSymbol symbol = new CustomSymbol(String.valueOf(symbolValue), TextComponentType.LETTER);
            component.add(symbol);
        }
    }
}
