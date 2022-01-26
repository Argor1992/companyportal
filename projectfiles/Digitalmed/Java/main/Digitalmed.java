package week1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Digitalmed {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfCases = Integer.parseInt(in.nextLine());

        for (int i = 0; i < numberOfCases; i++) {
            List<Integer> input = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            int withoutAdvertisement = input.get(0);
            int withAdvertisement = input.get(1) - input.get(2);

            if (withAdvertisement < withoutAdvertisement) {
                System.out.println("do not advertise");
            } else if (withAdvertisement > withoutAdvertisement) {
                System.out.println("advertise");
            } else {
                System.out.println("does not matter");
            }
        }
    }
}
