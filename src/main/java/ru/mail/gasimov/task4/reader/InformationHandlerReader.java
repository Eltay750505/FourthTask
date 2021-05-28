package ru.mail.gasimov.task4.reader;

import ru.mail.gasimov.task4.exception.InformationHandlerException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class InformationHandlerReader {
    public String readFromFile(String filePath) throws InformationHandlerException {
        try {
            Path path = Path.of(filePath);

            return Files.readString(path);
        } catch (InvalidPathException | IOException e) {
            throw new InformationHandlerException("Unable to open file: " + filePath, e);
        }
    }
}
