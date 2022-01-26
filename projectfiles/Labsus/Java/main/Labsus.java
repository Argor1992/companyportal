package week1;

import java.util.Scanner;

public class Labsus {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberStones = Integer.parseInt(in.nextLine());

        if (numberStones % 2 == 0)
            System.out.println("Bob");
        else
            System.out.println("Alice");
    }
}


