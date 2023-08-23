package by.itacademy.readerjson;

import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.Transport;

import java.util.List;

public interface TransportReaderJson {
    List<Transport> read()throws TransportReaderException;
    List<InvalidTransport> getInvalidTransport();
}
