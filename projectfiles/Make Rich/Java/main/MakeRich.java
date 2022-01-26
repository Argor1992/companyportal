package week1;

import java.util.Scanner;

public class MakeRich {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int mbPerMonth = Integer.parseInt(in.nextLine());
        int period = Integer.parseInt(in.nextLine());

        int result = mbPerMonth;
        for (int i = 0; i < period; i++) {
            int usedMb = Integer.parseInt(in.nextLine());
            result -= usedMb;
            result += mbPerMonth;
        }

        System.out.println(result);
    }
}
