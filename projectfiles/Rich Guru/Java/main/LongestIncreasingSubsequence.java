package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<String> results = new ArrayList<>();
        String input;
        while ((input = in.readLine()) != null && !input.isEmpty()) {
            int size = Integer.parseInt(input);

            String[] sequence = in.readLine().split(" ");
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(sequence[i]);
            }

            results.add(lis(arr));
        }

        results.forEach(System.out::println);
    }

    static String lis(int[] arr) {
        StringBuilder result = new StringBuilder();
        int[] lis = new int[arr.length];
        Arrays.fill(lis, 1);

        /* Compute optimized LIS values in
           bottom up manner */
        for (int i = 1; i < lis.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < lis.length; i++) {
            if (max < lis[i]) {
                max = lis[i];
                maxIndex = i;
            }
        }


        for (int i = maxIndex; i >= 0;) {
            for (int j = i; j >= 0; j--) {

            }
        }

//        result.append(max).append("\n");
//        for (int k = indexes.size()-1; k >= 0; k--) {
//            result.append(indexes.get(k)).append(" ");
//        }

        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println();
        Arrays.stream(lis).forEach(value -> System.out.print(value + " "));

        return result.toString();
    }
}
