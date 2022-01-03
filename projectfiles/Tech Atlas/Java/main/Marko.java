package week8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Marko {
    static Map<Character, Character> translateChar = Stream.of(new char[][] {
            {'a', '2'},
            {'b', '2'},
            {'c', '2'},
            {'d', '3'},
            {'e', '3'},
            {'f', '3'},
            {'g', '4'},
            {'h', '4'},
            {'i', '4'},
            {'j', '5'},
            {'k', '5'},
            {'l', '5'},
            {'m', '6'},
            {'n', '6'},
            {'o', '6'},
            {'p', '7'},
            {'q', '7'},
            {'r', '7'},
            {'s', '7'},
            {'t', '8'},
            {'u', '8'},
            {'v', '8'},
            {'w', '9'},
            {'x', '9'},
            {'y', '9'},
            {'z', '9'},
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberWords = Integer.parseInt(sc.nextLine());

        String[] words = new String[numberWords];
        for (int i = 0; i < numberWords; i++) {
            words[i] = sc.nextLine();
        }

        String buttonPresses = sc.nextLine();
        int combinations = 0;


        for (String word : words) {
            boolean works = true;
            for (int i = 0; i < word.length(); i++) {
                // check for each char in the word if it has the respective button press
                if (translateChar.get(word.charAt(i)) != buttonPresses.charAt(i)) {
                    works = false;
                    break;
                }
            }

            if (works)
                combinations++;
        }

        System.out.println(combinations);
    }
}
