package by.itacademy.sorting;

import by.itacademy.transports.ProcessedTransport;

import java.util.Comparator;
import java.util.function.Function;

public enum SortingDirection {

    ASC(Function.identity()),//вернется та же функция, которая была отправлена

    DSC(Comparator::reversed);//реверс

    private final Function<Comparator<ProcessedTransport>, Comparator<ProcessedTransport>> comparatorTransformer;//функциональный интерфейс преобразует один тип объекта в другой


    SortingDirection(final Function<Comparator<ProcessedTransport>, Comparator<ProcessedTransport>> comparatorTransformer) {
        this.comparatorTransformer = comparatorTransformer;
    }

    public Comparator<ProcessedTransport> transform(final Comparator<ProcessedTransport> comparator) {
        return comparatorTransformer.apply(comparator);//превращает один объект в другой
    }
}
