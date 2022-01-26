package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Zentech {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = in.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int capacity = Integer.parseInt(st.nextToken());
            int numberOfItems = Integer.parseInt(st.nextToken());

            int[] weight = new int[numberOfItems];
            int[] values = new int[numberOfItems];
            for (int i = 0; i < numberOfItems; i++) {
                st = new StringTokenizer(in.readLine());
                values[i] = Integer.parseInt(st.nextToken());
                weight[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[numberOfItems + 1][capacity + 1];
            for (int[] ints : dp) Arrays.fill(ints, -1);

            for (int i = 0; i <= numberOfItems; i++) {
                for (int j = 0; j <= capacity; j++) {
                    if (i == 0 || j == 0)
                        dp[i][j] = 0;
                    else if (weight[i - 1] <= j)
                        dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weight[i - 1]]);
                    else
                        dp[i][j] = dp[i - 1][j];
                }
            }

            int temp = capacity;
            List<Integer> indices = new ArrayList<>();
            for(int i = numberOfItems; i > 0; i--) {
                if(dp[i][temp] != dp[i - 1][temp]) {
                    indices.add(i - 1);
                    temp -= weight[i - 1];
                }
            }

            System.out.println(indices.size());
            for(int i : indices)
                System.out.print(i + " ");
            System.out.println();
        }
    }
}
