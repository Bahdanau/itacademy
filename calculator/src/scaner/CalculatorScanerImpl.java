package scaner;

import java.util.Scanner;

public class CalculatorScanerImpl implements CalculatorScaner {
    private static final Scanner SCANNER = new Scanner(System.in);

    public String nextLine() {
        return SCANNER.nextLine();
    }

    @Override
    public Double nextInt() {
        return SCANNER.nextDouble();
    }
}
