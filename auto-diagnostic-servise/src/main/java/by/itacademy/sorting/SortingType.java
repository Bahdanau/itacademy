package by.itacademy.sorting;

import by.itacademy.transports.ProcessedTransport;

import java.util.Comparator;
import java.util.function.Function;

public enum SortingType {

    TYPE(ProcessedTransport::getType),
    MODEL(ProcessedTransport::getModel),

    PRICE(ProcessedTransport::getPrice);

    private final Comparator<ProcessedTransport> transportComparator;

    <T extends Comparable<T>> SortingType(final Function<ProcessedTransport, T> function) {
        this.transportComparator = Comparator.comparing(function);
    }

    public Comparator<ProcessedTransport> getTransportComparator() {
        return transportComparator;
    }
}