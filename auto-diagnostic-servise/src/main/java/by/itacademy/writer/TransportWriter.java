package by.itacademy.writer;

import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.ProcessedTransport;

import java.util.Comparator;
import java.util.List;

public interface TransportWriter {
    List<TransportDestination> write(List<ProcessedTransport> processedTransports, Comparator<ProcessedTransport> comparator,
                                     List<InvalidTransport> invalidTransport) throws TransportWriterException;
}
