package by.itacademy.writer.impl;

import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.Transport;
import by.itacademy.writer.FileProcessingOptionType;
import by.itacademy.writer.TransportDestination;
import by.itacademy.writer.TransportWriter;
import by.itacademy.writer.TransportWriterException;
import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FileTransportWriter implements TransportWriter {

    private final File processedTransportFile;

    private final File invalidTransportFile;

    public FileTransportWriter(File processedTransportFile, File invalidTransportFile) {
        this.processedTransportFile = processedTransportFile;
        this.invalidTransportFile = invalidTransportFile;
    }

    @Override
    public List<TransportDestination> write(final List<Transport> transports, final Comparator<Transport> comparator,
                                            final List<InvalidTransport> invalidTransport) throws TransportWriterException {

        write(invalidTransportFile, invalidTransport);
        final TransportDestination invalidDestination = new TransportDestination("транспорт с ошибками",
                invalidTransportFile);

        transports.sort(comparator);
        write(processedTransportFile, transports);
        final TransportDestination processedDestination = new TransportDestination("продиагностированный транспорт",
                processedTransportFile);
        return List.of(invalidDestination, processedDestination);

    }

    private <T> void write(final File file, final List<T> content) throws TransportWriterException {

        final Scanner scanner = new Scanner(System.in);
        final String fileName = file.getName();
        final boolean result = FileProcessingOptionType.fileProcessingOptionReader(scanner, fileName);

        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, result))) {
            JSONArray jsonArray = new JSONArray(content);
            writer.write(jsonArray.toString());
        } catch (final IOException ex) {
            throw new TransportWriterException("Ошибка при записи файла " + file.getAbsolutePath(), ex);
        }
    }
}
