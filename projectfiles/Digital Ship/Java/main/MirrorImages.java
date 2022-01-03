package week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MirrorImages {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int cases = Integer.parseInt(in.nextLine());
        List<String> output = new ArrayList<>();
        for (int i = 0; i < cases; i++) {
            List<Integer> dimension = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

            String[] photo = new String[dimension.get(0)];
            for (int j = 0; j < dimension.get(0); j++) {
                String input = in.nextLine();
                photo[j] = reverseString(input);
            }
            output.add("Test " + (i+1));

            for (int j = photo.length - 1; j >= 0; j--)
                output.add(photo[j]);
        }

        output.forEach(System.out::println);
    }

    private static String reverseString(String input) {
        StringBuilder builder = new StringBuilder(input.length());

        for (int i = input.length()-1; i >= 0; i--) {
            builder.append(input.charAt(i));
        }

        return builder.toString();
    }
}
