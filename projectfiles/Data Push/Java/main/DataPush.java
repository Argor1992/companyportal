package week1;

import java.util.Scanner;

/**
 * FRANKFURT UAS EXERCISES WEEK 1, WS 21/22
 *
 * Problem: Jumbo Javelin
 * Link: https://open.kattis.com/problems/jumbojavelin
 * @author Jean Niklas L'orange
 * @author Thorsten Zieres, 1297197
 * @version 1.0, 10/27/2020
 * Method : Ad-Hoc
 * Status : Accepted
 * Runtime: 0.1
 */
public class DataPush {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int amount = Integer.parseInt(in.nextLine());

        int result = Integer.parseInt(in.nextLine());
        for (int i = 0; i < amount-1; i++) {
            int length = Integer.parseInt(in.nextLine());
            result += length - 1;
        }

        System.out.println(result);
    }
}
