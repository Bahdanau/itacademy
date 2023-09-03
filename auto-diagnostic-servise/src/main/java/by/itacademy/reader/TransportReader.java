package by.itacademy.reader;

import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.ProcessedTransport;

import java.util.List;

public interface TransportReader {
    List<ProcessedTransport> read()throws TransportReaderException;
    List<InvalidTransport> getInvalidTransport();
}
