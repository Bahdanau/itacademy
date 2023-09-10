package by.itacademy.sorting.impl;

import by.itacademy.scaner.ConsoleInput;
import by.itacademy.sorting.SortingReader;
import by.itacademy.sorting.SortingReaderException;
import by.itacademy.transports.ProcessedTransport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ConsoleSortingReaderTest {
    private final List<ProcessedTransport> processedTransports = new ArrayList<>();//используется только в этом классе, ссылка не будет меняться
    private final List<ProcessedTransport> processedTransportsTest = new ArrayList<>();//используется только в этом классе, ссылка не будет меняться

    @Test
    void readSorting_collections_success() throws SortingReaderException {//проверка как методом отсортирована коллекция
        //given
        final ConsoleInput consoleInput = Mockito.mock(ConsoleInput.class);// ссылка не будет меняться
        Mockito.when(consoleInput.nextLine()).thenReturn("PRICE ASC", "MODEL ASC", "");
        //when
        final SortingReader sortingReader = new ConsoleSortingReader(consoleInput);
        final Comparator<ProcessedTransport> comparator = sortingReader.readSorting();
        processedTransports.sort(comparator);
        //then
        Assertions.assertEquals(processedTransports.toString(), processedTransportsTest.toString());
        Mockito.verify(consoleInput, Mockito.times(3)).nextLine();
    }

    @BeforeEach
    void fillingCollection() {
        processedTransports.add(new ProcessedTransport("motorcycle", "Ninja ZX-3", 10));
        processedTransports.add(new ProcessedTransport("minibus", "Ninja ZX-2", 30));
        processedTransports.add(new ProcessedTransport("automobile", "Ninja ZX-2", 20));
        processedTransports.add(new ProcessedTransport("automobile", "Ninja ZX-1", 20));
        processedTransportsTest.add(new ProcessedTransport("motorcycle", "Ninja ZX-3", 10));
        processedTransportsTest.add(new ProcessedTransport("automobile", "Ninja ZX-1", 20));
        processedTransportsTest.add(new ProcessedTransport("automobile", "Ninja ZX-2", 20));
        processedTransportsTest.add(new ProcessedTransport("minibus", "Ninja ZX-2", 30));
    }
}