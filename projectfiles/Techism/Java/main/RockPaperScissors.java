package week8;

import java.util.Scanner;

public class RockPaperScissors {
    private static final String ROCK = "rock";
    private static final String PAPER = "paper";
    private static final String SCISSORS = "scissors";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String[] nAndK = sc.nextLine().split(" ");
            int numberOfPlayers = Integer.parseInt(nAndK[0]);
            if (numberOfPlayers == 0) break;
            int numberOfGames = Integer.parseInt(nAndK[1]);

            // player number >> wins, losses
            int[][] players = new int[numberOfPlayers][2];
            for(int i = 0; i < numberOfGames * numberOfPlayers * (numberOfPlayers - 1) / 2; i++) {
                String[] game = sc.nextLine().split(" ");
                int player1 = Integer.parseInt(game[0]);
                String movePlayer1 = game[1];
                int player2 = Integer.parseInt(game[2]);
                String movePlayer2 = game[3];

                if (movePlayer1.equals(movePlayer2)) {
                    continue;
                }

                String winner = determineWinner(movePlayer1, movePlayer2);
                if (movePlayer1.equals(winner)) {
                    players[player1-1][0]++;
                    players[player2-1][1]++;
                } else {
                    players[player2 - 1][0]++;
                    players[player1 - 1][1]++;
                }
            }

            for (int[] player : players) {
                int total = player[0] + player[1];
                System.out.println(total == 0 ? "-" : String.format("%.3f", (double) player[0] / total));
            }
            System.out.println();
        }
    }

    private static String determineWinner(String movePlayer1, String movePlayer2) {
        if(movePlayer1.equals(ROCK) && movePlayer2.equals(SCISSORS))
            return movePlayer1;
        if(movePlayer1.equals(SCISSORS) && movePlayer2.equals(PAPER))
            return movePlayer1;
        if(movePlayer1.equals(PAPER) && movePlayer2.equals(ROCK))
            return movePlayer1;
        return movePlayer2;
    }
}
