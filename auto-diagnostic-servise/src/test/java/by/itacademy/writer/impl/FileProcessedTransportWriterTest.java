package by.itacademy.writer.impl;

import by.itacademy.reader.impl.FileTransportReader;
import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.ProcessedTransport;

import by.itacademy.writer.TransportWriterException;
import by.itacademy.reader.TransportReaderException;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;


import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static by.itacademy.reader.impl.FileProcessedTransportReaderTest.*;
import static org.junit.jupiter.api.Assertions.*;

class FileProcessedTransportWriterTest {
    private static final List<ProcessedTransport> processedTransports = new ArrayList<>();
    private static final List<InvalidTransport> invalidTransports = new ArrayList<>();
    private static final String INVALID_TRANSPORTS_FILE = "src\\test\\resources\\invalid-transport.json";
    private static final String PROCESSED_TRANSPORTS_FILE = "src\\test\\resources\\processed-transport.json";
    private static final Comparator<ProcessedTransport> comparator = new Comparator<ProcessedTransport>() {
        @Override
        public int compare(ProcessedTransport o1, ProcessedTransport o2) {
            return 0;
        }
    };

    @Test
    void testWrite() throws TransportWriterException, TransportReaderException {
        processedTransports.add(new ProcessedTransport("motorcycle", "Ninja ZX-14", 10));
        invalidTransports.add(new InvalidTransport("automobile", "Audi Q9!№"));

        final var fileTransportWriter = new FileTransportWriter(new File(PROCESSED_TRANSPORTS_FILE),
                new File(INVALID_TRANSPORTS_FILE));
        final var writer = fileTransportWriter.write(processedTransports, comparator, invalidTransports);

        assertNotNull(writer, "Document is null");
        testRead();
    }

    void testRead() throws TransportReaderException {
        final var fileTransportReader = new FileTransportReader(PROCESSED_TRANSPORTS_FILE);
        final var processedTransporReader = fileTransportReader.read();

        final var fileInvalidTransportsReader = new FileTransportReader(INVALID_TRANSPORTS_FILE);
        final var invalidTransports = fileInvalidTransportsReader.getInvalidTransport();
        final var invalidTransportsReader = fileInvalidTransportsReader.read();

        assertEquals(processedTransporReader, PROCESSED_TRANSPORT);
        assertEquals(invalidTransports, INVALID_TRANSPORT);
    }

    <T> void assertEquals(final List<T> content, final Object object) {
        for (Object read : content) {
            Assertions.assertEquals(read.toString(), object.toString());
        }
    }
}