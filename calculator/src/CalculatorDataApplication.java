import calculator.Calculator;
import calculator.CalculatorImpl;
import scaner.CalculatorScaner;
import scaner.CalculatorScanerImpl;

public class CalculatorDataApplication {
    public static void main(String[] args) {
        final CalculatorScaner calculatorScaner = new CalculatorScanerImpl();

        System.out.println("Добро пожаловать в калькулятор");
        System.out.println("Введите что вы хотите \"делить\" или \"умножать\"");

        final Calculator calculator = new CalculatorImpl(calculatorScaner);

        final Double calculatorImpl = calculator.calculator();

        System.out.println("Ваш результат равен: "+calculatorImpl);
    }
}