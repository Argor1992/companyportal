package week8;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KittenOnATree {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String kitten = scan.next();
        scan.nextLine();

        Map<String, String> tree = new HashMap<>();

        while (true) {
            String s = scan.next();

            if (s.equals("-1")) {
                break;
            } else {
                String[] leaf = scan.nextLine().split(" ");
                for (String value : leaf) tree.put(value, s);
            }

        }

        String currentNode = kitten;
        while (true) {
            System.out.print(currentNode);

            if (!tree.containsKey(currentNode)) {
                break;
            } else {
                System.out.print(" ");
                currentNode = tree.get(currentNode);
            }
        }
    }
}
