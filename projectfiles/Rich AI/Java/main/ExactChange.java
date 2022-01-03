package week6;

import java.util.Arrays;
import java.util.Scanner;

public class ExactChange {
    private static final int MAX_VALUE_OF_COIN = 10_000;
    private static final int MAX_VALUE_OF_ALL_COINS = 10_000 * 100;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numberCases = Integer.parseInt(in.nextLine());

        for (int i = 0; i < numberCases; i++) {
            int amountToPay = Integer.parseInt(in.nextLine());
            int numberBills = Integer.parseInt(in.nextLine());

            int[] bills = new int[numberBills];
            for (int j = 0; j < numberBills; j++) {
                bills[j] = Integer.parseInt(in.nextLine());
            }

            System.out.println(computeSolution(amountToPay, bills));
        }
    }

    private static String computeSolution(int amountToPay, int[] bills) {
        int[] dp = new int[MAX_VALUE_OF_ALL_COINS + 1];
        Arrays.fill(dp, MAX_VALUE_OF_COIN);
        dp[0] = 0;

        for (int billValue : bills) {
            for (int j = MAX_VALUE_OF_ALL_COINS; j >= billValue; j--) {
                dp[j] = Math.min(dp[j], dp[j - billValue] + 1);
            }

//            for (int j = 0; j < dp.length; j++) {
//                if (dp[j] != MAX_VALUE_OF_COIN)
//                    System.out.print("(" + j + ", " + dp[j] + ") ");
//            }
//            System.out.println();
        }

        int i = amountToPay;
        while (dp[i] == MAX_VALUE_OF_COIN) i++;

        return i + " " + dp[i];
    }
}
