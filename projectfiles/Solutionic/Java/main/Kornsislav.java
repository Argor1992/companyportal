package week8;

import java.util.Arrays;
import java.util.Scanner;

public class Kornsislav {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] steps = new int[4];

        for(int i=0;i<4;i++)
            steps[i]= sc.nextInt();

        Arrays.sort(steps);
        System.out.println(steps[0]*steps[2]);
    }
}
