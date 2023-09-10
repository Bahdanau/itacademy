package by.itacademy.scaner;

import java.util.Scanner;

public class ConsoleImputImpl implements ConsoleInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    public String nextLine() {
        return SCANNER.nextLine();
    }
}
