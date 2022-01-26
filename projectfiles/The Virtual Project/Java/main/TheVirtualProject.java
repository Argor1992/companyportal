package week5;

import java.util.Scanner;

public class TheVirtualProject {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            String[] input = in.nextLine().split(" ");
            int n = Integer.parseInt(input[0]);
            double t = Double.parseDouble(input[1]);
            if (n == 0) break;

            System.out.printf("%.3f\n", getExpectedPrice(n, t));
        }
    }

    private static double getExpectedPrice(int n, double t) {
        double expectedPrice = Math.pow(2, n);
        double eq, prize;

        for (int i = n - 1; i >= 0; i--) {
            prize = Math.pow(2, i);
            eq = prize / expectedPrice;
            if (eq <= t)
                expectedPrice = (t + 1) / 2 * expectedPrice;
            else
                expectedPrice = (eq - t) / (1 - t) * prize +
                        (1 - eq) / (1 - t) * (eq + 1) / 2 * expectedPrice;
        }

        return expectedPrice;
    }
}
