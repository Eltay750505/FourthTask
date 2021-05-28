package ru.mail.gasimov.task4.parser;

public class ParserFactory {
    private ParserFactory() {

    }

    public static AbstractParser createParser() {
        LetterParser letterParser = new LetterParser();
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        LexemeParser lexemeParser = new LexemeParser();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(letterParser);

        return paragraphParser;
    }
}
