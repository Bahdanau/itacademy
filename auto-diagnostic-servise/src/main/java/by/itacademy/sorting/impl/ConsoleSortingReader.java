package by.itacademy.sorting.impl;

import by.itacademy.sorting.*;
import by.itacademy.transports.ProcessedTransport;
import by.itacademy.scaner.ConsoleInput;

import java.util.*;

public class ConsoleSortingReader implements SortingReader {
    private static final String SORTING_MESSAGE = "Введите поле для сортировки, одно из:"//используется только внутри класса, нельзя изменить ссылку, будет использоваться для всех объектов и принадлежит всему классу
            + "\n   " + Arrays.toString(SortingType.values())
            + "\nи через пробел напрвление сортировки, одно из:"
            + "\n   " + Arrays.toString(SortingDirection.values())
            + "\nили нажмите 'Enter' для завершения ввода";
    private final ConsoleInput consoleInput;//используется только внутри класса, нельзя изменить ссылку

    public ConsoleSortingReader(ConsoleInput consoleInput) {
        this.consoleInput = consoleInput;
    }

    @Override
    public Comparator<ProcessedTransport> readSorting() throws SortingReaderException {
        try {
            final List<Sorting> sortingList = new ArrayList<>(SortingType.values().length);//считает количество сортировок

            while (sortingList.size() < SortingType.values().length) {//будет выполняться пока не достигнет макс. количества сортировок
                final Sorting sorting = readSorting(consoleInput);//запускает приватный метод и записывает его в переменную

                if (sorting == null) {// если количесто выбранных сортировок 0 ставит метод на паузу
                    break;
                }
                final SortingType sortingType = sorting.getSortingType();//запись типа сортировки
                final boolean hasSorting = sortingList.stream().anyMatch(s -> s.getSortingType().equals(sortingType));// проверяет соблюдение условий сортировки интерфейс Predicate

                if (hasSorting) {
                    System.out.println("Сортировка " + sortingType + " уже добавлена, выберите другую сортировку.");//если true выводит
                } else {
                    sortingList.add(sorting);//добавляет сортировку если false
                }
            }
            System.out.println("Введено типов сортировок: " + sortingList.size());
            return sortingList.stream()
                    .map(Sorting::getComparator)//возвращает сортировку
                    .reduce((t1, t2) -> 0, Comparator::thenComparing);//принимает элементы потока, метод позволяет использовать цепочки компораторов
        } catch (final RuntimeException ex) {
            throw new SortingReaderException("Ошибка чтения сортировки для транспорта", ex);
        }
    }

    private Sorting readSorting(ConsoleInput consoleInput) {
        System.out.println(SORTING_MESSAGE);

        final String sorting = consoleInput.nextLine();

        if (sorting == null || sorting.isEmpty()) {
            return null;
        }
        final String[] parts = sorting.split("\\s");//бьет строку на 2 слова
        final SortingType sortingType = SortingType.valueOf(parts[0].toUpperCase());// запись типа сортировки
        final SortingDirection sortingDirection = SortingDirection.valueOf(parts[1].toUpperCase());// запись по возр. или убыванию

        return new Sorting(sortingType, sortingDirection);
    }
}
