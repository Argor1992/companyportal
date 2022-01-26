package week2;

import java.util.*;

public class RichCast {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = Integer.parseInt(in.nextLine());

        // starting and ending prime number
        List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String[] input = in.nextLine().split(" ");
            int first = Integer.parseInt(input[0]);
            int second = Integer.parseInt(input[1]);
            ranges.add(new Range(first, second));
        }

        // get prime numbers with four digits (Sieve of Eratosthenes)
        List<Integer> primes = getPrimeNumbers();

        // build an adjacent matrix, primes represent nodes, nodes are connected if only one digit differs
        int[][] matrix = getAdjacentMatrix(primes);

        for (Range range : ranges) {
            int shortestPathLength = getShortestPath(matrix, range, primes);
            System.out.println(shortestPathLength != -1 ? shortestPathLength : "Impossible");
        }
    }

    // uses A* algorithm - similar to Dijkstra but also uses an estimated distance to get quicker to the target
    private static int getShortestPath(int[][] graph, Range range, List<Integer> primes) {
        if (range.first == range.second)
            return 0;

        int start = primes.indexOf(range.first);
        int end = primes.indexOf(range.second);

        Map<Integer, Node> q = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            q.put(i, new Node(i, primes.get(i), Integer.MAX_VALUE, Integer.MAX_VALUE, null));
        }

        q.get(start).distance = 0;
        q.get(start).estimatedDistance = 0;
        List<Node> s = new ArrayList<>();

        Node currentNode;
        while (!q.isEmpty()) {
            currentNode = getMin(q);

            if (currentNode == null) {
                return -1;
            }

            s.add(currentNode);

            for (int i = 0; i < graph.length; i++) {
                if (graph[currentNode.index][i] == 1) {
                    Node n = q.get(i);
                    if (n != null) {
                        if (n.distance > currentNode.distance) {
                            n.distance = currentNode.distance + 1;
                            // to improve the runtime the difference between digits is used to direct the algorithm
                            n.estimatedDistance = n.distance + differByDigits(n.number, range.second);
                            n.prevNode = currentNode;
                        }
                    }
                }
            }

            if (currentNode.index == end) {
                break;
            }
        }

        if (s.isEmpty())
            return -1;

        return countPath(s.get(0), s.get(s.size() - 1));
    }

    private static int countPath(Node start, Node end) {
        List<Node> path = new ArrayList<>();

        while (true) {
            if (end == null)
                return -1;
            path.add(end);
            if (end.index == start.index)
                break;
            end = end.prevNode;
        }

        return path.size() - 1;
    }

    // returns the node with the minimum estimated distance
    private static Node getMin(Map<Integer, Node> q) {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Node> entry : q.entrySet()) {
            if (entry.getValue().estimatedDistance < min) {
                minIndex = entry.getKey();
                min = entry.getValue().estimatedDistance;
            }
        }

        return q.remove(minIndex);
    }

    private static class Node {
        public int index;
        public int number;
        public int distance;
        public int estimatedDistance;
        public Node prevNode;

        public Node(int index, int number, int distance, int estimatedDistance, Node prevNode) {
            this.index = index;
            this.number = number;
            this.distance = distance;
            this.estimatedDistance = estimatedDistance;
            this.prevNode = prevNode;
        }
    }

    private static int[][] getAdjacentMatrix(List<Integer> primes) {
        int[][] result = new int[primes.size()][primes.size()];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (differByDigits(primes.get(i), primes.get(j)) == 1) {
                    result[i][j] = 1;
                } else if (Objects.equals(primes.get(i), primes.get(j))){
                    result[i][j] = 0;
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        return result;
    }

    private static int differByDigits(int integer, int integer1) {
        String s1 = String.valueOf(integer);
        String s2 = String.valueOf(integer1);

        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                count++;
        }
        return count;
    }

    // returns prime numbers with four digits
    private static List<Integer> getPrimeNumbers() {
        int min = 1000;
        int max = 9999;
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

    private static class Range {
        public int first;
        public int second;

        public Range(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
