package by.itacademy;

import by.itacademy.reader.impl.FileTransportReader;
import by.itacademy.sorting.SortingReader;
import by.itacademy.sorting.impl.ConsoleSortingReader;
import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.Transport;
import by.itacademy.writer.TransportDestination;
import by.itacademy.writer.TransportWriter;
import by.itacademy.writer.impl.FileTransportWriter;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class TransportDataApplication {
    public static void main(String[] args) {
        System.out.println("Старт программы Диагностика транспорта");

        try {
            final FileTransportReader reader = new FileTransportReader("transport.json");
            final List<Transport> transports = reader.read();
            final List<InvalidTransport> invalidTransport = reader.getInvalidTransport();

            final SortingReader sortingReader = new ConsoleSortingReader();
            final Comparator<Transport> comparator = sortingReader.readSorting();

            final TransportWriter writer = new FileTransportWriter(new File("processed-transport.json"),
                    new File("invalid-transport.json"));
            final List<TransportDestination> destinations = writer.write(transports, comparator, invalidTransport);

            destinations.forEach(System.out::println);

        } catch (final Exception ex) {
            System.err.println("Ошибка работы программы. " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("Конец программы Диагностика транспорта");
    }
}
