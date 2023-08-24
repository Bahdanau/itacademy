package by.itacademy.writer;

import java.io.File;

public class TransportDestination {

    private final String description;
    private final File file;

    public TransportDestination(final String description, final File file) {
        this.description = description;
        this.file = file;
    }

    @Override
    public String toString() {
        return "Файл: " + file.getAbsolutePath() + " - " + description;
    }
}
