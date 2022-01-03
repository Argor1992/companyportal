package week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MixedFractions {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] input = in.nextLine().split(" ");
        List<String> results = new ArrayList<>();
        while (!input[0].equals("0")) {
            results.add(getMixedFraction(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
            input = in.nextLine().split(" ");
        }

        results.forEach(System.out::println);
    }

    private static String getMixedFraction(int numerator, int denominator) {
        int leading = numerator / denominator;
        numerator %= denominator;

        return String.format("%d %d / %d", leading, numerator, denominator);
    }
}
