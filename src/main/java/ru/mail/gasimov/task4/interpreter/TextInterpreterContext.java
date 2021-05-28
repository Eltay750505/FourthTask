package ru.mail.gasimov.task4.interpreter;

import ru.mail.gasimov.task4.exception.InformationHandlerException;
import ru.mail.gasimov.task4.interpreter.expression.TextExpression;
import ru.mail.gasimov.task4.interpreter.expression.impl.NumberExpression;
import ru.mail.gasimov.task4.interpreter.expression.impl.OperationExpression;

import java.util.ArrayDeque;
import java.util.Deque;

public class TextInterpreterContext {
    private static final String EXPRESSION_DELIMITER_REGEX =
            "(?<=\\d)(?=[&|^><])|(?<=[&|^><])(?=\\d)|(?<=\\()|(?<=\\))|(?=\\))|(?=\\()";


    private static final String OPENING_PARENTHESIS = "(";
    private static final String CLOSING_PARENTHESIS = ")";

    public int evaluate(String stringExpression) throws InformationHandlerException {
        String[] tokens = stringExpression.split(EXPRESSION_DELIMITER_REGEX);

        Deque<TextExpression> polishNotationDeque = toReversePolishNotation(tokens);
        TextExpression syntaxTree = buildExpressionTree(polishNotationDeque);

        return syntaxTree.interpret();
    }

    private TextExpression buildExpressionTree(Deque<TextExpression> expressions) {
        Deque<TextExpression> bufferDeque = new ArrayDeque<>();

        for (TextExpression expression : expressions) {
            if (expression.getClass() == OperationExpression.class) {
                TextExpression secondOperand = bufferDeque.removeLast();
                TextExpression firstOperand = bufferDeque.removeLast();
                OperationExpression operationExpression = (OperationExpression) expression;
                operationExpression.initializeOperands(firstOperand, secondOperand);
                bufferDeque.add(operationExpression);
            } else {
                bufferDeque.add(expression);
            }
        }

        return bufferDeque.removeFirst();
    }

    private Deque<TextExpression> toReversePolishNotation(String[] tokens) throws InformationHandlerException {
        Deque<TextExpression> result = new ArrayDeque<>();
        Deque<OperationType> operationBuffer = new ArrayDeque<>();

        Deque<Integer> parenthesisDeque = new ArrayDeque<>();

        for (String token : tokens) {
            if (token.equals(OPENING_PARENTHESIS)) {
                parenthesisDeque.addLast(0);
            } else if (token.equals(CLOSING_PARENTHESIS)) {
                int operationsAmount = parenthesisDeque.removeLast();

                while (operationsAmount-- != 0) {
                    OperationType operationType = operationBuffer.removeLast();
                    TextExpression operationExpression = new OperationExpression(operationType);
                    result.add(operationExpression);
                }
            } else if (OperationType.contains(token)) {
                OperationType operationType = OperationType.findByValue(token);
                int operationPriority = operationType.getPriority();
                int operationsAmount;

                while (!operationBuffer.isEmpty() && operationBuffer.peekLast().getPriority() >= operationPriority) {
                    OperationType poppedOperation = operationBuffer.removeLast();
                    TextExpression operationExpression = new OperationExpression(poppedOperation);
                    result.add(operationExpression);

                    if (!parenthesisDeque.isEmpty()) {
                        operationsAmount = parenthesisDeque.removeLast();
                        parenthesisDeque.addLast(--operationsAmount);
                    }
                }

                operationBuffer.add(operationType);

                if (!parenthesisDeque.isEmpty()) {
                    operationsAmount = parenthesisDeque.removeLast();
                    parenthesisDeque.addLast(++operationsAmount);
                }
            } else {
                try {
                    int value = Integer.parseInt(token);
                    TextExpression operandExpression = new NumberExpression(value);
                    result.add(operandExpression);
                } catch (NumberFormatException e) {
                    throw new InformationHandlerException("Invalid token: " + token, e);
                }
            }
        }

        while (!operationBuffer.isEmpty()) {
            OperationType operationType = operationBuffer.removeLast();
            TextExpression operationExpression = new OperationExpression(operationType);
            result.add(operationExpression);
        }

        return result;
    }
}
