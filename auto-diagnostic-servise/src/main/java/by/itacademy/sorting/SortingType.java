package by.itacademy.sorting;

import by.itacademy.transports.ProcessedTransport;

import java.util.Comparator;
import java.util.function.Function;

public enum SortingType {

    TYPE(ProcessedTransport::getType),//передается ссылка на метод
    MODEL(ProcessedTransport::getModel),//передается ссылка на метод
    PRICE(ProcessedTransport::getPrice);//передается ссылка на метод

    private final Comparator<ProcessedTransport> transportComparator;//используется только Enam, нельзя изменить ссылку

    <T extends Comparable<T>> SortingType(final Function<ProcessedTransport, T> function) {//функциональный интерфейс преобразует один тип объекта в другой
        this.transportComparator = Comparator.comparing(function);
    }

    public Comparator<ProcessedTransport> getTransportComparator() {
        return transportComparator;
    }
}