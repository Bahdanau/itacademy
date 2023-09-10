package by.itacademy.writer;

import java.io.File;

public class TransportDestination {

    private final String description;//используется только внутри класса, нельзя изменить ссылку
    private final File file;//используется только внутри класса, нельзя изменить ссылку

    public TransportDestination(final String description, final File file) {
        this.description = description;
        this.file = file;
    }

    @Override
    public String toString() {
        return "Файл: " + file.getAbsolutePath() + " - " + description;
    }
}
