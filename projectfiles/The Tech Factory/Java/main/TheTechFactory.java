package week5;

import java.util.Scanner;

public class TheTechFactory {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            String[] input = in.nextLine().split(" ");
            if (input[0].equals("-1")) break;

            long m = Long.parseLong(input[0]);
            long n = Long.parseLong(input[1]);

            if (m == n) {
                System.out.println(getZerosOfNumber(m));
            } else {
                // functions return zeros from 0 to the parameter
                // this is easier to handle
                long amount = numberOfZeros(n) - numberOfZeros(m - 1);
                System.out.println(amount);
            }
        }
    }

    private static int getZerosOfNumber(long m) {
        if (m == 0) {
            return 1;
        }

        int amount = 0;
        while (m > 0) {
            amount += (m%10 == 0) ? 1 : 0;
            m /= 10;
        }
        return amount;
    }

    private static long numberOfZeros(long n) {
        if (n == -1L) {
            return 0L;
        }

        long numberOfZeros = 0L;
        long multiple = 10L;
        String number = String.valueOf(n);

        // count all zeros in the middle
        for (int i = number.length() - 2; i > 0; i--) {
            String sl = number.substring(0, i);
            long leftNumber = Integer.parseInt(sl);

            String sr = number.substring(i + 1);
            long rightNumber = Integer.parseInt(sr);

            long add = 0L;

            if (number.charAt(i) != '0') {
                add += multiple * leftNumber;
            } else {
                add += multiple * (leftNumber - 1L) + rightNumber + 1L;
            }

            numberOfZeros += add;
            multiple *= 10L;
        }

        numberOfZeros += 1 + (n / 10L);

        return numberOfZeros;
    }
}
