package week1;

import java.util.Scanner;

public class Microly {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] numbers = input.split(" ");
        System.out.println(Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]));
    }
}
