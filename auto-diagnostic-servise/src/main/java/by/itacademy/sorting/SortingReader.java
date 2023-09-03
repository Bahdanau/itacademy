package by.itacademy.sorting;

import by.itacademy.transports.ProcessedTransport;

import java.util.Comparator;

public interface SortingReader {
    Comparator<ProcessedTransport> readSorting() throws SortingReaderException;
}
