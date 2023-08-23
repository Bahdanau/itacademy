package by.itacademy.sorting;

import by.itacademy.transports.Transport;

import java.util.Comparator;

public interface SortingReader {
    Comparator<Transport> readSorting() throws SortingReaderException;
}
