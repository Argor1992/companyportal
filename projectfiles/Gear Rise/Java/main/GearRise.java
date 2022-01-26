package week1;

import java.util.Scanner;

public class GearRise {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfPeriods = Integer.parseInt(in.nextLine());

        double result = 0;
        for (int i = 0; i < numberOfPeriods; i++) {
            String input = in.nextLine();
            String[] numbers = input.split(" ");
            result += Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
        }

        System.out.println(result);
    }
}


