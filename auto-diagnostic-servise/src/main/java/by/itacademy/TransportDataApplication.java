package by.itacademy;

import by.itacademy.reader.TransportReader;
import by.itacademy.reader.impl.FileTransportReader;
import by.itacademy.sorting.SortingReader;
import by.itacademy.sorting.impl.ConsoleSortingReader;
import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.ProcessedTransport;
import by.itacademy.writer.TransportDestination;
import by.itacademy.writer.TransportWriter;
import by.itacademy.writer.impl.FileTransportWriter;
import by.itacademy.scaner.ConsoleInput;
import by.itacademy.scaner.ConsoleImputImpl;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class TransportDataApplication {
    public static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) {
        System.out.println("Старт программы Диагностика транспорта");
        try {
            final TransportReader reader = new FileTransportReader(RESOURCES+"transport.json");
            final List<ProcessedTransport> processedTransports = reader.read();
            final List<InvalidTransport> invalidTransport = reader.getInvalidTransport();

            final ConsoleInput consoleInput = new ConsoleImputImpl();


            final SortingReader sortingReader = new ConsoleSortingReader(consoleInput);
            final Comparator<ProcessedTransport> comparator = sortingReader.readSorting();

            final TransportWriter writer = new FileTransportWriter(new File("src/main/resources/processed-transport.json"),
                    new File("src/main/resources/invalid-transport.json"), consoleInput);
            final List<TransportDestination> destinations = writer.write(processedTransports, comparator, invalidTransport);

            destinations.forEach(System.out::println);

        } catch (final Exception ex) {
            System.err.println("Ошибка работы программы. " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("Конец программы Диагностика транспорта");
    }
}
