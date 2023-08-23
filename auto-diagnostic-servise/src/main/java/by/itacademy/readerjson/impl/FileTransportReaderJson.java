package by.itacademy.readerjson.impl;


import by.itacademy.readerjson.TransportReaderException;
import by.itacademy.readerjson.TransportReaderJson;
import by.itacademy.transports.InvalidTransport;
import by.itacademy.transports.Transport;
import by.itacademy.transports.TransportType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileTransportReaderJson implements TransportReaderJson {
    final static String REGEX_MODEL = "^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$";
    private static String fileName;
    final StringBuilder stringBuilder = new StringBuilder();
    final List<InvalidTransport> invalidTransports = new ArrayList<>();
    final List<Transport> transports = new ArrayList<>();

    public FileTransportReaderJson(final String fileName) {
        this.fileName = fileName;

    }

    @Override
    public List<Transport> read() throws TransportReaderException {

        try {
            String contents = new String((Files.readAllBytes(Paths.get(fileName))));
            JSONArray array = new JSONArray(contents);
            for (int index = 0; index < array.length(); index++) {
                stringBuilder.append(array.get(index) + ".");
            }
            String[] words = stringBuilder.toString().split("\\.");

            processingJson(words);

            return transports;
        } catch (IOException ex) {
            throw new TransportReaderException("Ошибка при чтении файла " + fileName, ex);
        } catch (final IllegalArgumentException ex) {
            throw new TransportReaderException("Ошибка определения типа транспорта", ex);
        } catch (final RuntimeException ex) {
            throw new TransportReaderException("Ошибка анализа прочтенного файла", ex);
        }
    }

    @Override
    public List<InvalidTransport> getInvalidTransport() {
        return invalidTransports;
    }

    private void processingJson(String[] words) {

        for (String word : words) {
            JSONObject jsonObject = new JSONObject(word);
            final TransportType transportType = TransportType.valueOf(jsonObject.getString("type").toUpperCase());
            final String model = jsonObject.getString("model");
            final Integer price = transportType.getPrice();
            if ((Pattern.matches(REGEX_MODEL, model))) {
                transports.add(new Transport(transportType.name().toLowerCase(), model, price));
            } else {
                invalidTransports.add(new InvalidTransport(transportType.name().toLowerCase(), model));
            }
        }
    }
}
