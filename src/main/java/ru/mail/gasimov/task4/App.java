package ru.mail.gasimov.task4;

import ru.mail.gasimov.task4.exception.InformationHandlerException;
import ru.mail.gasimov.task4.reader.InformationHandlerReader;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InformationHandlerException {
        String filePath = "src\\main\\resources\\data\\data.txt";
        InformationHandlerReader reader = new InformationHandlerReader();
        String[] strings = reader.readFromFile(filePath);
        String name = Arrays
                .stream(Arrays.copyOf(strings, strings.length ))
                .sequential()
                .collect(Collectors.joining());
        System.out.println(name);
    }
}
