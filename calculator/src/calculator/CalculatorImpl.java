package calculator;

import scaner.CalculatorScaner;

public class CalculatorImpl implements Calculator {
    private final CalculatorScaner calculatorScaner;
    private Double firstValue;
    private Double secondValue;
    private Double result;

    public CalculatorImpl(CalculatorScaner calculatorScaner) {
        this.calculatorScaner = calculatorScaner;
    }

    @Override
    public Double calculator() {
        String in = calculatorScaner.nextLine();
        dataForCalculator();

        if (in.toLowerCase().equals("умножать")) {
            result = multiplication();
        } else if (in.toLowerCase().equals("делить")) {
            result = division();
        } else {
            System.out.println("неверный ввод");
        }
        return result;
    }

    private Double multiplication() {
        System.out.println();
        Double result = firstValue * secondValue;
        return result;
    }

    private Double division() {
        Double result = firstValue / secondValue;
        return result;
    }

    private void dataForCalculator() {
        System.out.println("Напишите первое значение");
        firstValue = calculatorScaner.nextInt();
        System.out.println("Напишите второе значение");
        secondValue = calculatorScaner.nextInt();
    }
}
