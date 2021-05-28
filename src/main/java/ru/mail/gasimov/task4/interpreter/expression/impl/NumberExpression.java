package ru.mail.gasimov.task4.interpreter.expression.impl;

import ru.mail.gasimov.task4.interpreter.expression.TextExpression;

public class NumberExpression implements TextExpression {
    private final int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int interpret() {
        return value;
    }
}
