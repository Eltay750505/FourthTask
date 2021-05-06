package ru.mail.gasimov.task4.reader;

import ru.mail.gasimov.task4.exception.InformationHandlerException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class InformationHandlerReader {
    public String[] readFromFile(String filePath) throws InformationHandlerException {
        if (filePath == null || filePath.isEmpty()){
            throw new InformationHandlerException("Passed wrong pathName : " + filePath);
        }
        try {
            Path path = Paths.get(filePath);


            try (Stream<String> stream = Files.lines(path)) {
                String[] strings = stream.toArray(String[]::new);

                if (strings.length == 0) {
                    throw new InformationHandlerException("There is no correct strings in file : " + filePath);
                }

                return strings;
            }
        } catch (InvalidPathException | IOException e) {
            throw new InformationHandlerException("Cannot Open current file: " + filePath, e);
        }
    }
}
