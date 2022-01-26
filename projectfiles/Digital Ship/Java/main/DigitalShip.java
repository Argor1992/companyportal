package week1;

import java.util.Scanner;

public class DigitalShip {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        boolean duplicate = false;
        for (int i = 0; i < input.length()-1  && !duplicate; i++) {
            for (int j = i+1; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    duplicate = true;
                    break;
                }
            }
        }

        System.out.println(duplicate ? "0" : "1");
    }
}


