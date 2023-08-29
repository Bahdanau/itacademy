package by.itacademy.writer;

import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.Transport;

import java.util.Comparator;
import java.util.List;

public interface TransportWriter {
    List<TransportDestination> write(List<Transport> transports, Comparator<Transport> comparator,
                                     List<InvalidTransport> invalidTransport) throws TransportWriterException;
}
