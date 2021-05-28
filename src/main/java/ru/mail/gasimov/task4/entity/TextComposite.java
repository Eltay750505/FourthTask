package ru.mail.gasimov.task4.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class TextComposite extends TextComponent {
    private final List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentType textComponentType) {
        super(textComponentType);
    }

    @Override
    public void add(TextComponent textComponent) {
        components.add(textComponent);
    }

    @Override
    public void addAll(Collection<? extends TextComponent> items) {
        components.addAll(items);
    }

    @Override
    public void removeAll(Collection<? extends TextComponent> items) {
        components.removeAll(items);
    }

    @Override
    public void remove(TextComponent textComponent) {
        components.remove(textComponent);
    }

    @Override
    public Collection<TextComponent> getChildrenAsCollection() {
        return new ArrayList<>(components);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = prime * result + components.hashCode();
        result = prime * result + getTextComponentType().hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TextComposite textComposite = (TextComposite) obj;
        boolean equality = components.equals(textComposite.components);
        equality &= textComposite.getTextComponentType() != null
                ? textComposite.getTextComponentType().equals(getTextComponentType())
                : textComposite.getTextComponentType() == getTextComponentType();

        return equality;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        components.forEach(component -> {
            TextComponentType type = component.getTextComponentType();
            String prefix = type.getPrefix();
            String suffix = type.getSuffix();
            stringBuilder.append(prefix).append(component).append(suffix);
        });

        return (getTextComponentType() != TextComponentType.TEXT)
                ? stringBuilder.toString().strip()
                : stringBuilder.toString().stripTrailing();
    }
}
