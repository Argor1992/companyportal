package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BachetsGame {

    private static List<Integer> m;
    private static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = in.readLine()) != null && !input.isEmpty()) {
            String[] line = input.split(" ");
            int numberOfStones = Integer.parseInt(line[0]);
            m = Arrays.stream(Arrays.copyOfRange(line, 2, line.length)).map(Integer::parseInt).collect(Collectors.toList());

            memo = new int[numberOfStones + 5];
            Arrays.fill(memo, -1);

            if (whichPlayerWins(numberOfStones)) {
                System.out.println("Stan wins");
            } else {
                System.out.println("Ollie wins");
            }
        }

    }

    private static boolean whichPlayerWins(int numberOfStones) {
        if (numberOfStones == 0) {
            return false;
        }

        if (memo[numberOfStones] != -1) return memo[numberOfStones] == 1;

        boolean win = false;

        for (int i : m) {
            if (numberOfStones - i < 0) continue;
            if (!whichPlayerWins(numberOfStones - i)) {
                win = true;
                break;
            }
        }

        memo[numberOfStones] = win ? 1 : 0;
        return memo[numberOfStones] == 1;
    }
}
