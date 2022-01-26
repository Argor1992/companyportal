package week1;

import java.util.Scanner;

public class MetaLabs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] heightAndBase = in.nextLine().split(" ");
        System.out.println(0.5 * Integer.parseInt(heightAndBase[0]) * Integer.parseInt(heightAndBase[1]));
    }
}


