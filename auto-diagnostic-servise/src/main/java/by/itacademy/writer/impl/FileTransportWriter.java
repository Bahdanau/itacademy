package by.itacademy.writer.impl;

import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.ProcessedTransport;
import by.itacademy.writer.FileProcessingOptionType;
import by.itacademy.writer.TransportDestination;
import by.itacademy.writer.TransportWriter;
import by.itacademy.writer.TransportWriterException;
import by.itacademy.scaner.ConsoleInput;
import org.json.JSONArray;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

public class FileTransportWriter implements TransportWriter {

    private final File processedTransportFile;//используется только внутри класса, нельзя изменить ссылку

    private final File invalidTransportFile;//используется только внутри класса, нельзя изменить ссылку
    private final ConsoleInput consoleInput;//используется только внутри класса, нельзя изменить ссылку


    public FileTransportWriter(File processedTransportFile, File invalidTransportFile, ConsoleInput consoleInput) {
        this.processedTransportFile = processedTransportFile;
        this.invalidTransportFile = invalidTransportFile;
        this.consoleInput = consoleInput;
    }

    @Override
    public List<TransportDestination> write(final List<ProcessedTransport> processedTransports, final Comparator<ProcessedTransport> comparator,
                                            final List<InvalidTransport> invalidTransport) throws TransportWriterException {
        write(invalidTransportFile, invalidTransport);
        final TransportDestination invalidDestination = new TransportDestination("транспорт с ошибками",
                invalidTransportFile);//нельзя изменить ссылку
        processedTransports.sort(comparator);
        write(processedTransportFile, processedTransports);
        final TransportDestination processedDestination = new TransportDestination("продиагностированный транспорт",
                processedTransportFile);//нельзя изменить ссылку
        return List.of(invalidDestination, processedDestination);
    }

    private <T> void write(final File file, final List<T> content) throws TransportWriterException {
        boolean result= fileProcessingOptionType(file);

        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, result))) {
            final JSONArray jsonArray = new JSONArray(content);
            writer.write(jsonArray.toString());
        } catch (final IOException ex) {
            throw new TransportWriterException("Ошибка при записи файла " + file.getAbsolutePath(), ex);
        }
    }

    private boolean fileProcessingOptionType(File file) {//используется только внутри класса
        final String fileName = file.getName();//нельзя изменить ссылку
        System.out.println("Очистить файл " + fileName + ": введите YES или NO.");
        final String scanner = consoleInput.nextLine();//нельзя изменить ссылку
        final FileProcessingOptionType writerq = FileProcessingOptionType.valueOf(scanner.toUpperCase());//нельзя изменить ссылку
        final boolean result = writerq.isResult();//нельзя изменить ссылку

        return result;
    }
}
