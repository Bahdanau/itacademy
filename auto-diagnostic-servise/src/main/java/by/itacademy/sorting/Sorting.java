package by.itacademy.sorting;

import by.itacademy.transports.ProcessedTransport;

import java.util.Comparator;

public class Sorting {// класс типов сортировки
    private final SortingType sortingType;//используется только внутри класса, нельзя изменить ссылку
    private final SortingDirection sortingDirection;//используется только внутри класса, нельзя изменить ссылку

    public Sorting(final SortingType sortingType, final SortingDirection sortingDirection) {
        this.sortingType = sortingType;
        this.sortingDirection = sortingDirection;
    }

    public SortingType getSortingType() {
        return sortingType;
    }

    public SortingDirection getSortingDirection() {
        return sortingDirection;
    }

    public Comparator<ProcessedTransport> getComparator() {
        return sortingDirection.transform(sortingType.getTransportComparator());
    }
}
