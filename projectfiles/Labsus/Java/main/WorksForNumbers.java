package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WorksForNumbers {
    public static final Map<Integer, String> WORD = Map.ofEntries(
            Map.entry(0, "zero"),
            Map.entry(1, "one"),
            Map.entry(2, "two"),
            Map.entry(3, "three"),
            Map.entry(4, "four"),
            Map.entry(5, "five"),
            Map.entry(6, "six"),
            Map.entry(7, "seven"),
            Map.entry(8, "eight"),
            Map.entry(9, "nine"),
            Map.entry(10, "ten"),
            Map.entry(11, "eleven"),
            Map.entry(12, "twelve"),
            Map.entry(13, "thirteen"),
            Map.entry(14, "fourteen"),
            Map.entry(15, "fifteen"),
            Map.entry(16, "sixteen"),
            Map.entry(17, "seventeen"),
            Map.entry(18, "eighteen"),
            Map.entry(19, "nineteen"),
            Map.entry(20, "twenty"),
            Map.entry(30, "thirty"),
            Map.entry(40, "forty"),
            Map.entry(50, "fifty"),
            Map.entry(60, "sixty"),
            Map.entry(70, "seventy"),
            Map.entry(80, "eighty"),
            Map.entry(90, "ninety")
    );

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<String> results = new ArrayList<>();
        String input;
        while ((input = in.readLine()) != null && !input.isEmpty()) {
            results.add(getSentence(input));
        }
        results.forEach(System.out::println);
    }

    private static String getSentence(String input) {
        StringBuilder result = new StringBuilder(input.length());
        String[] words = input.split(" ");

        if (words[0].charAt(0) >= '0' && words[0].charAt(0) <= '9') {
            String firstNumber = numberToWord(words[0]);
            firstNumber = Character.toUpperCase(firstNumber.charAt(0)) + firstNumber.substring(1);
            result.append(firstNumber);
        } else {
            result.append(words[0]);
        }
        result.append(" ");

        for (int i = 1; i < words.length; i++) {
            if (words[i].charAt(0) >= '0' && words[i].charAt(0) <= '9') {
                result.append(numberToWord(words[i]));
            } else {
                result.append(words[i]);
            }

            if (i < words.length-1)
                result.append(" ");
        }

        return result.toString();
    }

    public static String numberToWord(String number) {
        if (number.length() == 1) {
            return WORD.get(Integer.parseInt(number));
        } else if (number.length() == 2) {
            if (number.charAt(0) == '1') {
                return WORD.get(Integer.parseInt(number));
            } else {
                if (number.charAt(1) == '0') {
                    return WORD.get(Integer.parseInt(number));
                }
                return WORD.get(Integer.parseInt(String.valueOf(number.charAt(0))) * 10) + "-"
                        + WORD.get(Integer.parseInt(String.valueOf(number.charAt(1))));
            }
        }
        return number;
    }
}
