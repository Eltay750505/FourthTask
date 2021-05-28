package ru.mail.gasimov.task4.entity;

public enum TextComponentType {
    TEXT,
    PARAGRAPH("    ", "\r\n"),
    SENTENCE(" ", ""),
    WORD(" ", ""),
    PUNCTUATION,
    LETTER;

    private String prefix = "";
    private String suffix = "";

    TextComponentType() {
        // default prefix and suffix
    }

    TextComponentType(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
}
