package ru.mail.gasimov.task4.entity;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class CustomSymbol extends CustomTextComponent {
    private static final Logger LOGGER = LogManager.getLogger();
    private final char character;

    public CustomSymbol(char value) {
        super(CustomTextComponentType.SYMBOL);
        this.character = value;
    }


    @Override
    public void add(CustomTextComponent textComponent) {
        LOGGER.log(Level.INFO, "Cannot add new component to existing leaf");
    }

    @Override
    public void remove(CustomTextComponent textComponent) {
        LOGGER.log(Level.INFO, "Cannot new component to existing leaf");
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public int hashCode() {
        return Objects.hash(character);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomSymbol that = (CustomSymbol) o;
        return character == that.character;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomSymbol{");
        sb.append("character=").append(character);
        sb.append(", textComponentType=").append(this.getTextComponentType().toString());
        sb.append('}');
        return sb.toString();
    }
}
