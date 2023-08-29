package by.itacademy.reader;

import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.Transport;

import java.util.List;

public interface TransportReader {
    List<Transport> read()throws TransportReaderException;
    List<InvalidTransport> getInvalidTransport();
}
