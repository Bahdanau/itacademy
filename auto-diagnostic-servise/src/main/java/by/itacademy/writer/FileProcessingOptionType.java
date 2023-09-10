package by.itacademy.writer;

import java.io.File;
import java.util.Scanner;

public enum FileProcessingOptionType {

    YES(false),
    NO(true);
    private final boolean result;//нельзя изменить ссылку, используется только тут

    FileProcessingOptionType(final boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

}
