package by.itacademy.reader.impl;

import by.itacademy.reader.TransportReader;
import by.itacademy.reader.TransportReaderException;
import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.ProcessedTransport;
import by.itacademy.transports.TransportType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileTransportReader implements TransportReader {
    private final static String REGEX_MODEL = "^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$";//используется только внутри класса, нельзя изменить ссылку,будет использоваться для всех объектов и принадлежит всему классу, модель всегда должна проходить проверку
    private final String fileName; //используется только внутри класса, нельзя изменить ссылку
    private final StringBuilder stringBuilder = new StringBuilder();//используется только внутри класса, нельзя изменить ссылку
    private final List<InvalidTransport> invalidTransports = new ArrayList<>();//используется только внутри класса, нельзя изменить ссылку
    private final List<ProcessedTransport> processedTransports = new ArrayList<>();//используется только внутри класса, нельзя изменить ссылку

    public FileTransportReader(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<ProcessedTransport> read() throws TransportReaderException {
        try {
            final String contents = new String((Files.readAllBytes(Paths.get(fileName))));//нельзя изменить ссылку
            final JSONArray array = new JSONArray(contents);//нельзя изменить ссылку

            for (int index = 0; index < array.length(); index++) {
                stringBuilder.append(array.get(index) + ".");
            }

            final String[] words = stringBuilder.toString().split("\\."); //нельзя изменить ссылку
            processingJson(words);

            return processedTransports;
        } catch (IOException ex) {
            throw new TransportReaderException("Ошибка при чтении файла " + fileName);
        } catch (final IllegalArgumentException ex) {
            throw new TransportReaderException("Ошибка определения типа транспорта");
        } catch (final RuntimeException ex) {
            throw new TransportReaderException("Ошибка анализа прочтенного файла");
        }
    }

    @Override
    public List<InvalidTransport> getInvalidTransport() {
        return invalidTransports;
    }

    private void processingJson(String[] words) {
        for (String word : words) {
            final JSONObject jsonObject = new JSONObject(word);//нельзя изменить ссылку
            final TransportType transportType = TransportType.valueOf(jsonObject.getString("type").toUpperCase());//нельзя изменить ссылку
            final String model = jsonObject.getString("model");//нельзя изменить ссылку
            final Integer price = transportType.getPrice();//нельзя изменить ссылку

            if ((Pattern.matches(REGEX_MODEL, model))) {
                processedTransports.add(new ProcessedTransport(transportType.name().toLowerCase(), model, price));
            } else {
                invalidTransports.add(new InvalidTransport(transportType.name().toLowerCase(), model));
            }
        }
    }
}