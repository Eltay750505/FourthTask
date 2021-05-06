package ru.mail.gasimov.task4.entity;

import java.util.ArrayList;
import java.util.List;

public class CustomTextComposite extends CustomTextComponent {
    private final List<CustomTextComponent> components = new ArrayList<>();

    public CustomTextComposite(CustomTextComponentType textComponentType) {
        super(textComponentType);
    }

    @Override
    public void add(CustomTextComponent textComponent) {
        components.add(textComponent);
    }

    @Override
    public void remove(CustomTextComponent textComponent) {
        components.remove(textComponent);
    }

    @Override
    public String toString() {
        String separator = getTextComponentType().getSeparator();
        String[] stringComponents = components.stream()
                .map(Object::toString)
                .toArray(String[]::new);

        return String.join(separator, stringComponents);
    }
}
