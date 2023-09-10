package by.itacademy.writer.impl;

import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.ProcessedTransport;

import by.itacademy.writer.TransportDestination;
import by.itacademy.writer.TransportWriterException;

import by.itacademy.scaner.ConsoleInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FileProcessedTransportWriterTest {
    private static final String INVALID_TRANSPORTS_FILE = "src\\test\\resources\\invalid-transport.json";//используется только в этом классе, ссылка не будет меняться, будет использоваться для всех объектов и принадлежит всему классу
    private static final String PROCESSED_TRANSPORTS_FILE = "src\\test\\resources\\processed-transport.json";//используется только в этом классе, ссылка не будет меняться, будет использоваться для всех объектов и принадлежит всему классу
    private final List<ProcessedTransport> processedTransports = new ArrayList<>();//используется только в этом классе, ссылка не будет меняться
    private final List<InvalidTransport> invalidTransports = new ArrayList<>();//используется только в этом классе, ссылка не будет меняться
    private final String invalidTransportTest = "[{\"model\":\"Audi Q9!№\",\"type\":\"automobile\"}]";//используется только в этом классе, ссылка не будет меняться
    private final String processedTransportTest = "[{\"price\":10,\"model\":\"Ninja ZX-14\",\"type\":\"motorcycle\"}]";//используется только в этом классе, ссылка не будет меняться
    private final Comparator<ProcessedTransport> comparator = (o1, o2) -> 0;//используется только в этом классе, ссылка не будет меняться

    @Test
    void testWrite_fileTransport_success() throws TransportWriterException, IOException {//проверка коллекций в которые записал метод
        //given
        final ConsoleInput consoleInput = Mockito.mock(ConsoleInput.class);// ссылка не будет меняться
        Mockito.when(consoleInput.nextLine()).thenReturn("YES");
        final FileTransportWriter fileTransportWriter = new FileTransportWriter(new File(PROCESSED_TRANSPORTS_FILE),
                new File(INVALID_TRANSPORTS_FILE), consoleInput);// ссылка не будет меняться
        final String contentsTestInvalidTransport = new String((Files.readAllBytes(Paths.get(INVALID_TRANSPORTS_FILE))));// ссылка не будет меняться
        final String contentsTestProcessedTransport = new String((Files.readAllBytes(Paths.get(PROCESSED_TRANSPORTS_FILE))));// ссылка не будет меняться
        //when
        final List<TransportDestination> writer = fileTransportWriter.write(processedTransports, comparator, invalidTransports);// ссылка не будет меняться
        //then
        assertNotNull(contentsTestInvalidTransport,"File is null");
        assertNotNull(contentsTestProcessedTransport,"File is null");
        Assertions.assertEquals(contentsTestInvalidTransport, invalidTransportTest);
        Assertions.assertEquals(contentsTestProcessedTransport, processedTransportTest);
        Mockito.verify(consoleInput, Mockito.times(2)).nextLine();
    }
    @BeforeEach
    void fillingCollection () {
        processedTransports.add(new ProcessedTransport("motorcycle", "Ninja ZX-14", 10));
        invalidTransports.add(new InvalidTransport("automobile", "Audi Q9!№"));
    }
}