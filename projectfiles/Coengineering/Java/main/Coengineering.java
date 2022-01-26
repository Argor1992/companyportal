package other;

import java.util.Scanner;

public class Coengineering {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();

        int[] numbers = new int[number];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = in.nextInt();
        }

        for (int i = numbers.length-1; i >= 0; i--) {
            System.out.println(numbers[i]);
        }
    }
}
