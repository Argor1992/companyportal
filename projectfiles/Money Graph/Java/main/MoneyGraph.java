package week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MoneyGraph {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int amount = Integer.parseInt(in.nextLine());
        int max = 0;

        int[] numbers = new int[amount];
        for (int i = 0; i < amount; i++) {
            numbers[i] = Integer.parseInt(in.nextLine());
            if (numbers[i] > max)
                max = numbers[i];
        }

        List<Integer> primeNumbers = getPrimeNumbers(max);

        for (int n : numbers) {
            List<String> results = new ArrayList<>();
            // just go through all primes to n/2 and check if the other operant is also a prime
            for (int j = 0; primeNumbers.get(j) <= n/2; j++) {
                int p = primeNumbers.get(j);
                int q = n - p;
                if (primeNumbers.contains(q))
                    results.add(p + "+" + q);
            }

            System.out.println(n + " has " + results.size() + " representation(s)");
            results.forEach(System.out::println);
        }
    }

    // Sieve of Eratosthenes
    private static List<Integer> getPrimeNumbers(int n) {
        boolean[] sieve = new boolean[n];
        Arrays.fill(sieve, true);

        for (int i = 2; i*i <= n; i++) {
            if(sieve[i]) {
                for (int j = i*i; j < n; j+=i)
                    sieve[j] = false;
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < sieve.length; i++) {
            if (sieve[i])
                primes.add(i);
        }

        return primes;
    }
}