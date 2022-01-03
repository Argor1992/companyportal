package week6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DigitSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = Integer.parseInt(in.nextLine());

        List<Long> results = new ArrayList<>();
        for (int i = 0; i < cases; i++) {
            String[] interval = in.nextLine().split(" ");
            results.add(getDigitSumsOfInterval(Long.parseLong(interval[0]), Long.parseLong(interval[1])));
        }
        results.forEach(System.out::println);
    }

    private static long getDigitSumsOfInterval(long a, long b) {
        long sum = 0;
        long remainder = a;

        while (remainder != 0) {
            sum += remainder % 10;
            remainder /= 10;
        }

        return digitSums(b) - digitSums(a) + sum;
    }

    private static long digitSums(long n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;

        long last = n % 10;
        long remainder = n / 10;

        if (last == 9) {
            return digitSums(remainder) * 10 + (remainder + 1) * 45;
        } else {
            long sum = 0;
            long r = remainder;

            while (r != 0) {
                sum += r % 10;
                r /= 10;
            }

            long partial = (last + 1) * sum + ((last * last + last) / 2);
            remainder -= 1;

            return digitSums(remainder)* 10 + (remainder + 1) * 45 + partial;
        }
    }
}
