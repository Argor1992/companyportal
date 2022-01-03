package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FundamentalNeighbors {

    private static List<Integer> primes;

    public static void main(String[] args) throws IOException {
        primes = getPrimeNumbers((int)(Math.ceil(Math.sqrt(Integer.MAX_VALUE))));

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<String> results = new ArrayList<>();
        String input;
        while ((input = in.readLine()) != null) {
            int number = Integer.parseInt(input);

            results.add(number + " " + getNeighbor(number));
        }

        results.forEach(System.out::println);
    }

    private static int getNeighbor(int number) {
        List<PrimeFactor> primeFactors = getPrimeFactors(number);

        int result = 1;

        for (PrimeFactor primeFactor : primeFactors)
            result *= Math.pow(primeFactor.exponent, primeFactor.base);

        return result;
    }

    private static List<PrimeFactor> getPrimeFactors(int number) {
        List<PrimeFactor> primeFactors = new ArrayList<>();

        int temp = number;
        int exponent;
        for (int i = 0; i < primes.size(); i++) {
            exponent = 0;
            if (primes.get(i) > number) {
                return primeFactors;
            }

            while (temp % primes.get(i) == 0) {
                exponent++;
                temp /= primes.get(i);
            }

            if (exponent > 0)
                primeFactors.add(new PrimeFactor(primes.get(i), exponent));
        }

        return primeFactors;
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

    private static class PrimeFactor {
        public int base;
        public int exponent;

        public PrimeFactor(int base, int exponent) {
            this.base = base;
            this.exponent = exponent;
        }
    }
}
