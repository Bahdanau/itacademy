package by.itacademy.reader.impl;

import by.itacademy.reader.TransportReaderException;
import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.ProcessedTransport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileProcessedTransportReaderTest {
    public static final String TEST_FILE_NAME = "src\\test\\resources\\test-transport.json";
    public static final ProcessedTransport PROCESSED_TRANSPORT = new ProcessedTransport("motorcycle", "Ninja ZX-14", 10);
    public static final InvalidTransport INVALID_TRANSPORT = new InvalidTransport("automobile", "Audi Q9!№");

    @Test
    void testRead() throws TransportReaderException {
        final var transportReader = new FileTransportReader(TEST_FILE_NAME);
        final var invalidTransportReader = transportReader.getInvalidTransport();

        final var processedTransportsReader = transportReader.read();

        assertNotNull(TEST_FILE_NAME, "File is null");
        assertEquals(processedTransportsReader, PROCESSED_TRANSPORT);
        assertEquals(invalidTransportReader, INVALID_TRANSPORT);
    }
    <T> void assertEquals(final List<T> content, final Object object) {
        for (Object read : content) {
            Assertions.assertEquals(read.toString(), object.toString());
        }
    }
}