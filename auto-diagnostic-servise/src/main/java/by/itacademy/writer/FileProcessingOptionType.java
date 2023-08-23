package by.itacademy.writer;

import java.util.Scanner;

public enum FileProcessingOptionType {

    YES(false),
    NO(true);
    private final boolean result;

    FileProcessingOptionType(final boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public static boolean fileProcessingOptionReader(Scanner scanner, String fileName) {
        System.out.println("Очистить файл " + fileName + ": введите YES или NO.");
        final String choce = scanner.nextLine();
        final FileProcessingOptionType writer = FileProcessingOptionType.valueOf(choce.toUpperCase());
        final boolean result = writer.isResult();
        return result;
    }
}
