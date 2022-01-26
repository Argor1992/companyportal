package week1;

import java.util.Scanner;

public class Enchipica {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int iterations = in.nextInt();

        int numberOfEdges = 2;
        int increasingBy = 1;
        for (int i = 0; i < iterations; i++) {
            numberOfEdges += increasingBy;
            increasingBy *= 2;
        }

        System.out.println(numberOfEdges*numberOfEdges);
    }
}


