package week2;

import java.util.*;

public class NumbersSet {
    private static List<Integer> primes;

    public static void main(String[] args) {
        primes = getPrimeNumbers((int) Math.ceil(Math.sqrt(10000)));

        Scanner in = new Scanner(System.in);
        int cases = Integer.parseInt(in.nextLine());

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < cases; i++) {
            String[] input = in.nextLine().split(" ");
            results.add(solve(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2])));
        }


        int i = 1;
        for (Integer result : results)
            System.out.println("Case #" + (i++) + ": " + result);
    }

    private static int solve(int a, int b, int p) {
        List<Set<Integer>> sets = new ArrayList<>();
        for (int i = a; i <= b; i++) {
            Set<Integer> list = new HashSet<>();
            list.add(i);
            sets.add(list);
        }

        for (int i = a; i <= b; i++) {
            Set<Integer> currentSet = getCurrentSet(sets, i);
            for (int j = i + 1; j <= b; j++) {
                if (havePrimeFactor(i, j, p)) {
                    Set<Integer> otherSet = getCurrentSet(sets, j);
                    currentSet.addAll(otherSet);
                }
            }
            sets.add(currentSet);
        }

        return sets.size();
    }

    private static Set<Integer> getCurrentSet(List<Set<Integer>> sets, Integer a) {
        for (Set<Integer> set : sets) {
            if (set.contains(a)) {
                sets.remove(set);
                return set;
            }

        }
        return Collections.emptySet();
    }

    private static boolean havePrimeFactor(int a, int b, int p) {
        List<Integer> factorsA = getPrimeFactors(a);
        List<Integer> factorsB = getPrimeFactors(b);

        for (Integer factor : factorsA)
            if (factor >= p && factorsB.contains(factor))
                return true;

        return false;
    }

    private static List<Integer> getPrimeFactors(int a) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < primes.size(); i++) {
            if (primes.get(i) > a)
                return result;
            if (a % primes.get(i) == 0)
                result.add(primes.get(i));
        }

        return result;
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
