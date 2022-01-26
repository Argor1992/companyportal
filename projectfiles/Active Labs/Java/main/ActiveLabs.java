package other;

import java.util.Scanner;

public class ActiveLabs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfTemperatures = Integer.parseInt(in.nextLine());
        String[] temperatures = in.nextLine().split(" ");

        int count = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (Integer.parseInt(temperatures[i]) < 0)
                count++;
        }
        System.out.println(count);
    }
}
