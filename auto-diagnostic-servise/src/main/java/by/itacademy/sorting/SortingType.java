package by.itacademy.sorting;

import by.itacademy.transports.Transport;

import java.util.Comparator;
import java.util.function.Function;

public enum SortingType {

    TYPE(Transport::getType),
    MODEL(Transport::getModel),

    PRICE(Transport::getPrice);

    private final Comparator<Transport> transportComparator;

    <T extends Comparable<T>> SortingType(final Function<Transport, T> function) {
        this.transportComparator = Comparator.comparing(function);
    }

    public Comparator<Transport> getTransportComparator() {
        return transportComparator;
    }
}