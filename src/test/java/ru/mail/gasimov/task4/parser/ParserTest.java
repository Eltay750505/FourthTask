package ru.mail.gasimov.task4.parser;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.mail.gasimov.task4.entity.TextComponent;
import ru.mail.gasimov.task4.entity.TextComponentType;
import ru.mail.gasimov.task4.entity.TextComposite;
import ru.mail.gasimov.task4.exception.InformationHandlerException;
import ru.mail.gasimov.task4.reader.InformationHandlerReader;

import java.io.IOException;

public class ParserTest {
    private AbstractParser parser;

    @BeforeClass
    public void setUp() {
        LetterParser letterParser = new LetterParser();
        LexemeParser lexemeParser = new LexemeParser();
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(letterParser);

        parser = paragraphParser;
    }

    @DataProvider(name = "correct-text")
    public Object[][] correctTextProvider() throws InformationHandlerException {
        String path = "src\\test\\resources\\testText";
        Object[][] data = new Object[1][];
        InformationHandlerReader reader = new InformationHandlerReader();
        String fromFile = reader.readFromFile(path);

        data[0] = new Object[] { fromFile };


        return data;
    }

    @DataProvider(name = "text-provider")
    public Object[][] textProvider() throws InformationHandlerException {
        String[] filePaths = {
                "src\\test\\resources\\smalText",
                "src\\test\\resources\\text",
                "src\\test\\resources\\bigText"
        };

        Object[][] data = new Object[filePaths.length][];

        for (int i = 0; i < filePaths.length; i++) {
            InformationHandlerReader reader = new InformationHandlerReader();
            String fromFile = reader.readFromFile(filePaths[i]);

            data[i] = new Object[] { fromFile };
        }

        return data;
    }

    @Test(dataProvider = "text-provider")
    public void testParseNotEquals(String initialText) throws InformationHandlerException {
        TextComponent textComposite = new TextComposite(TextComponentType.TEXT);
        parser.parse(textComposite, initialText);
        String resultText = textComposite.toString();

        Assert.assertNotEquals(resultText, initialText);
    }

    @Test(dataProvider = "correct-text")
    public void testParseEquals(String initialText) throws InformationHandlerException {
        TextComponent textComposite = new TextComposite(TextComponentType.SENTENCE);
        parser.parse(textComposite, initialText);

        String resultText = textComposite.toString();

        Assert.assertEquals(resultText, initialText);
    }
}
