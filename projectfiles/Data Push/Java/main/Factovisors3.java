package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Factovisors3 {
    private static List<Integer> primes;

    public static void main(String[] args) throws IOException {
        primes = getPrimeNumbers(46350);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<String> results = new ArrayList<>();
        String input;
        while ((input = in.readLine()) != null && !input.isEmpty()) {
            String[] values = input.split(" ");

            long n = Long.parseLong(values[0]);
            long m = Long.parseLong(values[1]);
            results.add(isDivisible(n, m) ? String.format("%d divides %d!", m, n) : String.format("%d does not divide %d!", m, n));
        }

        results.forEach(System.out::println);
    }

    private static boolean isDivisible(long n, long m) {
        if (m == 0)
            return false;
        if (n == 0)
            return m == 1;

        Map<Integer, Integer> factors = new HashMap<>();
        boolean divisible = true;
        long temp = m;
        for (Integer prime : primes) {
            if ((long) prime * prime > temp) {
                if (n < temp)
                    divisible = false;
                break;
            }

            int sum = 0;
            while (temp % prime == 0) {
                temp /= prime;
                if (factors.get(prime) == null) {
                    factors.put(prime, 1);
                } else {
                    factors.replace(prime, factors.get(prime)+1);
                }
                sum += n / Math.pow(prime, factors.get(prime));

                if (sum < factors.get(prime)) {
                    return false;
                }

                if (temp == 1)
                    break;
            }
        }

        return divisible;
    }

    private static List<Integer> getPrimeNumbers(int max) {
        int min = 2;
        boolean[] sieve = new boolean[max];
        Arrays.fill(sieve, true);

        for (int i = 2; i * i <= max; i++) {
            if (sieve[i]) {
                for (int j = i * i; j < max; j += i)
                    sieve[j] = false;
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = min; i < max; i++) {
            if (sieve[i])
                primes.add(i);
        }

        return primes;
    }
}
