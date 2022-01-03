package week8;

import java.util.Arrays;
import java.util.Scanner;

public class PlantingTrees {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfSeeds = sc.nextInt();
        int[] trees = new int[numberOfSeeds];

        for (int i = 0; i < trees.length; i++) {
           trees[i] = sc.nextInt();
        }
        Arrays.sort(trees);

        // Plant trees beginning with the longest growth time to the least growth time
        int plantDay = 1;
        int daysUntilParty = 0;
        for(int i = numberOfSeeds - 1; i >= 0; i--) {
            trees[i] -= (numberOfSeeds - plantDay); // calculates how much the tree has grown during the planting of all trees
            daysUntilParty++;
            plantDay++;
        }

        // check if trees still need time to grow
        int daysLeft = 0;
        for (int i = 0; i < numberOfSeeds; i++) {
            if (trees[i] > daysLeft) {
                daysLeft = trees[i];
            }
        }
        daysUntilParty += daysLeft;
        daysUntilParty++; // add one because Party is on the next day after all trees are fully grown

        System.out.println(daysUntilParty);
    }
}
