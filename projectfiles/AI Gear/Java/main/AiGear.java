package other;

import java.util.Scanner;

public class AiGear {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] numbers = input.split(" ");

        int number1 = Integer.parseInt(numbers[0]);
        int number2 = Integer.parseInt(numbers[1]);
        int maxNumber = Integer.parseInt(numbers[2]);

        for (int i = 1; i <= maxNumber; i++) {
            if (i % number1 == 0 && i % number2 == 0)
                System.out.println("FizzBuzz");
            else if (i % number1 == 0)
                System.out.println("Fizz");
            else if (i % number2 == 0)
                System.out.println("Buzz");
            else
                System.out.println(i);
        }

    }
}
