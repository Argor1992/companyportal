package other;

import java.util.Scanner;

public class CoreMoneyTech {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        for (int i = 0; i < input.length()-1; i++) {
            char currentChar = input.charAt(i);

            if (currentChar == ':' || currentChar == ';') {
                if (input.charAt(i+1) == ')')
                    System.out.println(i);;

                if (i < input.length()-2 && input.charAt(i+1) == '-' && input.charAt(i+2) == ')')
                    System.out.println(i);;
            }
        }
    }
}
