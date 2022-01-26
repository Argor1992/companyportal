package week1;

import java.util.Scanner;

public class Dotech {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfIntegers = Integer.parseInt(in.nextLine());
        String[] integers = in.nextLine().split(" ");

        int sum = 0;
        for (int i = 0; i < integers.length; i++) {
            sum += Integer.parseInt(integers[i]);
        }

        System.out.println(sum);
    }
}
