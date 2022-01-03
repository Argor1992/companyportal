package week6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HoneycombWalk {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());

        List<Long> results = new ArrayList<>();
        for (int i = 0; i < testCases; i++) {
            results.add(computeWays(Integer.parseInt(in.nextLine())));
        }
        results.forEach(System.out::println);
    }

    private static long computeWays(int steps) {
        long result = 0;

        for (int i = 0; i <= steps; i++) {
            long firstPart = (long)Math.pow(-2, steps - i) * binomial(steps, i);

            long secondPart = 0;
            for (int j = 0; j <= i; j++) {
                secondPart += Math.pow(binomial(i, j), 3);
            }

            result += firstPart * secondPart;
        }

        return result;
    }

    private static long binomial(long x, long y) {
        if (x == y || y == 0)
            return 1;

        if (y == 1)
            return x;

        long result = x;
        for (long i = 1; i < y; i++) {
            result *= (x- i);
        }
        result /= factorial(y);

        return result;
    }

    private static long factorial(long y) {
        long result = y;

        for (long i = 1;  i < y; i++) {
            result *= (y - i);
        }

        return result;
    }
}
