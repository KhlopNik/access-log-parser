import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число: ");
        int firstNumber = Integer.parseInt(new Scanner(System.in).nextLine());

        System.out.println("Введите второе число: ");
        int secondNumber = Integer.parseInt(new Scanner(System.in).nextLine());

        int sum = firstNumber + secondNumber;
        System.out.println("Сумма чисел: " + sum);

        int diff = firstNumber - secondNumber;
        System.out.println("Разность чисел: " + diff);

        int multipl = firstNumber * secondNumber;
        System.out.println("Произведение чисел: " + multipl);

        double div = (double) firstNumber / secondNumber;
        System.out.println("Частное чисел: " + div);
    }
}