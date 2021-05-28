package ru.mail.gasimov.task4.entity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.gasimov.task4.exception.InformationHandlerException;

import java.util.Collection;

public class CustomSymbol extends TextComponent {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String value;

    public CustomSymbol(String value, TextComponentType componentType) {
        super(componentType);
        this.value = value;
    }

    public String getCharacter() {
        return value;
    }


    @Override
    public void add(TextComponent textComponent)  {
        String message = "An attempt to add child to symbol";
        LOGGER.error(message);
        throw new UnsupportedOperationException();    }

    @Override
    public void addAll(Collection<? extends TextComponent> items) {
        String message = "An attempt to add all child to symbols";
        LOGGER.error(message);
        throw new UnsupportedOperationException();    }

    @Override
    public void removeAll(Collection<? extends TextComponent> items) {
        String message = "An attempt to remove all child from symbols";
        LOGGER.error(message);
        throw new UnsupportedOperationException();    }

    @Override
    public void remove(TextComponent textComponent) {
        String message = "An attempt to remove child from symbol";
        LOGGER.error(message);
        throw new UnsupportedOperationException();    }

    @Override
    public Collection<TextComponent> getChildrenAsCollection() {
        String message = "An attempt to get stream of children from symbol";
        LOGGER.error(message);
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = prime * result + value.hashCode();
        result = prime * result + getTextComponentType().hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CustomSymbol symbol = (CustomSymbol) obj;
        boolean equality = symbol.value != null
                ? symbol.value.equals(value)
                : symbol.value == value;
        equality &= symbol.getTextComponentType() != null
                ? symbol.getTextComponentType().equals(getTextComponentType())
                : symbol.getTextComponentType() == getTextComponentType();

        return equality;
    }

    @Override
    public String toString() {
        return value;
    }
}
