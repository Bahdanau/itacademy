package by.itacademy.sorting;

import by.itacademy.transports.ProcessedTransport;

import java.util.Comparator;
import java.util.function.Function;

public enum SortingDirection {

    ASC(Function.identity()),

    DSC(Comparator::reversed);

    private final Function<Comparator<ProcessedTransport>, Comparator<ProcessedTransport>> comparatorTransformer;//


    SortingDirection(final Function<Comparator<ProcessedTransport>, Comparator<ProcessedTransport>> comparatorTransformer) {
        this.comparatorTransformer = comparatorTransformer;
    }

    public Comparator<ProcessedTransport> transform(final Comparator<ProcessedTransport> comparator) {
        return comparatorTransformer.apply(comparator);
    }
}
