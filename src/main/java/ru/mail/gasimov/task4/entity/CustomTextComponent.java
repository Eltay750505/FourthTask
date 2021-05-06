package ru.mail.gasimov.task4.entity;

public abstract class CustomTextComponent {
    private CustomTextComponentType textComponentType;

    public CustomTextComponent(CustomTextComponentType textComponentType) {
        this.textComponentType = textComponentType;
    }

    public CustomTextComponentType getTextComponentType() {
        return textComponentType;
    }

    public abstract void add(CustomTextComponent textComponent);

    public abstract void remove(CustomTextComponent textComponent);
}
