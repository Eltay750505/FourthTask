package ru.mail.gasimov.task4.entity;

public enum CustomTextComponentType {
    TEXT("\n"),

    PARAGRAPH(" "),

    SENTENCE(" "),

    WORD(""),

    SYMBOL("");

    private final String separator;

    CustomTextComponentType(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }
}
