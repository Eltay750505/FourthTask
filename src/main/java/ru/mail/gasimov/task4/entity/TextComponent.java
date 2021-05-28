package ru.mail.gasimov.task4.entity;

import ru.mail.gasimov.task4.exception.InformationHandlerException;

import java.util.Collection;

public abstract class TextComponent {
    private TextComponentType textComponentType;

    public TextComponent(TextComponentType textComponentType) {
        this.textComponentType = textComponentType;
    }

    public TextComponentType getTextComponentType() {
        return textComponentType;
    }

    public abstract void add(TextComponent textComponent);
    public abstract void addAll(Collection<? extends TextComponent> items);
    public abstract void removeAll(Collection<? extends TextComponent> items);
    public abstract Collection<TextComponent> getChildrenAsCollection();
    public abstract void remove(TextComponent textComponent);
}
