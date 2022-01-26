package week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VirtualStorm {
    /**
     * Largest continuous block in the array (max sum of sub-array: Kadane Algorithm)
     *
     * This is why dynamic is useful here:
     *
     * -2
     * -2, 15
     * -2, 15, -14
     * -2, 15, -14, 60
     * and so on: a lot of potential to remember stuff for later
     *
     * local_maximum[i] = max(A[i], A[i] + local_maximum[i-1])
     *
     *                      1   1
     *                  -5, 1  -4
     *              60, -5, 1  56
     *         -14, 60, -5, 1  42
     *     15, -14, 60, -5, 1  57
     * -2, 15, -14, 60, -5, 1  55
     *
     * -2, 15,   1, 61, 56, 57
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] np = in.nextLine().split(" ");
        int n = Integer.parseInt(np[0]);
        int p = Integer.parseInt(np[1]);
        String[] i = in.nextLine().split(" ");

        List<Integer> arr = Arrays.stream(i).map(s -> Integer.parseInt(s) - p).collect(Collectors.toList());

        System.out.println(kadaneAlgorithm(arr));
    }

    private static int kadaneAlgorithm(List<Integer> arr) {
        int globalMax = 0;
        int localMax = 0;

        for (int i = 0; i < arr.size(); i++) {
            localMax = Math.max(arr.get(i), arr.get(i) + localMax);

            if (localMax > globalMax)
                globalMax = localMax;
        }

        return globalMax;
    }

    private static int bruteForce(List<Integer> arr) {
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            List<Integer> subArray = new ArrayList<>();
            subArray.add(arr.get(i));
            for (int j = i+1; j < arr.size(); j++) {
                subArray.add(arr.get(j));
                int sum = sumOfArray(subArray);

                if (sum > max)
                    max = sum;
            }
        }

        return max;
    }

    private static int sumOfArray(List<Integer> arr) {
        int sum = 0;
        for (Integer i : arr)
            sum += i;

        return sum;
    }
}
