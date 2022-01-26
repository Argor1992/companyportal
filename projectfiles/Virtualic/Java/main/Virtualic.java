package week7;

import java.util.Arrays;
import java.util.Scanner;

public class Virtualic {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int length = in.nextInt();
            int numberOnPole = in.nextInt();

            int min = 0;
            int[] ants = new int[numberOnPole];
            for (int j = 0; j < ants.length; j++) {
                ants[j] = in.nextInt();
                min = Math.max(
                        Math.min(ants[j], length - ants[j]),
                        min);
            }
            Arrays.sort(ants);

            int max = Math.max(
                    Math.max(
                            Math.max(
                                    ants[0],
                                    ants[numberOnPole-1]
                            ),
                            length - ants[0]
                    ),
                    length - min
            );
            System.out.println(min + " " + max);
        }

    }
}
