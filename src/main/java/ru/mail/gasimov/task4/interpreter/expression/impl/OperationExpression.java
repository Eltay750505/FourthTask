package ru.mail.gasimov.task4.interpreter.expression.impl;

import ru.mail.gasimov.task4.interpreter.expression.TextExpression;

import java.util.function.BiFunction;

public class OperationExpression implements TextExpression {
    private final BiFunction<TextExpression, TextExpression, Integer> operation;
    private TextExpression firstOperand;
    private TextExpression secondOperand;

    public OperationExpression(BiFunction<TextExpression, TextExpression, Integer> operation) {
        this.operation = operation;
    }

    public void initializeOperands(TextExpression firstOperand, TextExpression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    public int interpret() {
        return (firstOperand != null && secondOperand != null)
                ? operation.apply(firstOperand, secondOperand)
                : 0;
    }
}
