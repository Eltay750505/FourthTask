package ru.mail.gasimov.task4.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.gasimov.task4.entity.TextComponent;
import ru.mail.gasimov.task4.exception.InformationHandlerException;

public abstract class AbstractParser {
    protected AbstractParser nextParser = DefaultTextParser.getParser();

    public void setNextParser(AbstractParser textParser) {
        this.nextParser = textParser;
    }

    public abstract void parse(TextComponent component, String data) throws InformationHandlerException;

    private static class DefaultTextParser extends AbstractParser {
        private static Logger logger = LogManager.getLogger();
        private static DefaultTextParser parser = new DefaultTextParser();

        public static DefaultTextParser getParser() {
            return parser;
        }

        @Override
        public void parse(TextComponent component, String data) {
            logger.info("End of parser chain");
        }
    }
}
