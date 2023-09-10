package by.itacademy.reader.impl;

import by.itacademy.reader.TransportReaderException;
import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.ProcessedTransport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileProcessedTransportReaderTest {
    private final static String testFileName = "src/test/resources/test-transport.json";// используется только в этом классе, ссылка не будет меняться, будет использоваться для всех объектов и принадлежит всему классу
    private final ProcessedTransport processedTransport = new ProcessedTransport("motorcycle", "Ninja ZX-14", 10);// используется только в этом классе, ссылка не будет меняться
    private final InvalidTransport invalidTransport = new InvalidTransport("automobile", "Audi Q9!№");// используется только в этом классе, ссылка не будет меняться

    @Test
    void testRead_fileTransport_success() throws TransportReaderException {//тест чтения файла и наполнение коллекций с последующей проверкой содержимого
        //given
        final FileTransportReader fileTransportReader = new FileTransportReader(testFileName);// ссылка не будет меняться
        final List<InvalidTransport> invalidTransportReader = fileTransportReader.getInvalidTransport();// ссылка не будет меняться
        //when
        final List<ProcessedTransport> processedTransportsReader = fileTransportReader.read();// ссылка не будет меняться
        //then
        assertNotNull(testFileName, "File is null");
        assertEquals(processedTransportsReader, processedTransport);
        assertEquals(invalidTransportReader, invalidTransport);
    }
    private <T> void assertEquals(final List<T> content, final Object object) {// используется только в этом классе, сравнивает объекты между собой
        for (Object read : content) {
            Assertions.assertEquals(read.toString(), object.toString());//сравнивает объекты в массиве
        }
    }
}