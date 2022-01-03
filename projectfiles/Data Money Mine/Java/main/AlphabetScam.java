package week3;

import java.util.Scanner;

public class AlphabetScam {
    public static void main(String[] args) {
        int numberWhiteSpace = 0;
        int numberLowerCase = 0;
        int numberUpperCase = 0;
        int numberSymbols = 0;

        Scanner in = new Scanner(System.in);
        String word = in.nextLine();

        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);

            if (current == '_')
                numberWhiteSpace++;
            else if (current >= 'a' && current <= 'z')
                numberLowerCase++;
            else if (current >= 'A' && current <= 'Z')
                numberUpperCase++;
            else
                numberSymbols++;
        }

        System.out.println((double)numberWhiteSpace / word.length());
        System.out.println((double)numberLowerCase / word.length());
        System.out.println((double)numberUpperCase / word.length());
        System.out.println((double)numberSymbols / word.length());
    }
}
