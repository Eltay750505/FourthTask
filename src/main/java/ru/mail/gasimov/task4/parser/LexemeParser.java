package ru.mail.gasimov.task4.parser;

import ru.mail.gasimov.task4.entity.CustomSymbol;
import ru.mail.gasimov.task4.entity.TextComponent;
import ru.mail.gasimov.task4.entity.TextComponentType;
import ru.mail.gasimov.task4.entity.TextComposite;
import ru.mail.gasimov.task4.exception.InformationHandlerException;
import ru.mail.gasimov.task4.interpreter.TextInterpreterContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser {
    private static final String LEXEME_SPLIT_REGEX = "\\s";
    private static final String WORD_REGEX = "[^.!?,]+";
    private static final String EXPRESSION_REGEX = "\\d+((<{2}|>{2,3}|&|\\||\\^)\\d+)+";
    private static final String PUNCTUATION_REGEX = "\\.|!|\\?|\\.{3}|,";

    @Override
    public void parse(TextComponent component, String data) throws InformationHandlerException {
        String[] lexemes = data.split(LEXEME_SPLIT_REGEX);
        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Pattern punctuationPattern = Pattern.compile(PUNCTUATION_REGEX);
        TextInterpreterContext interpreterContext = new TextInterpreterContext();

        for (String lexeme : lexemes) {
            Matcher wordMatcher = wordPattern.matcher(lexeme);
            Matcher punctuationMatcher = punctuationPattern.matcher(lexeme);

            while (wordMatcher.find()) {
                StringBuilder word = new StringBuilder(wordMatcher.group());

                if (Pattern.matches(EXPRESSION_REGEX, word)) {
                    int calculatedExpression = interpreterContext.evaluate(word.toString());
                    word.append(calculatedExpression);
                }

                TextComponent lexemeComponent = new TextComposite(TextComponentType.WORD);
                component.add(lexemeComponent);
                nextParser.parse(lexemeComponent, word.toString());
            }

            while (punctuationMatcher.find()) {
                String punctuation = punctuationMatcher.group();
                TextComponent lexemeComponent = new CustomSymbol(punctuation, TextComponentType.PUNCTUATION);
                component.add(lexemeComponent);
            }
        }
    }
}
