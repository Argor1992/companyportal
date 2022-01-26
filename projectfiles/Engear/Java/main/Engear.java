package week1;

import java.util.Scanner;

public class Engear {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());

        int result = 0;
        for (int i = 0; i < n; i++) {
            int currentNumber = Integer.parseInt(in.nextLine());
            int exponent = currentNumber % 10;
            int base = currentNumber/10;
            result += Math.pow(base, exponent);
        }

        System.out.println(result);
    }
}
