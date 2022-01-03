package week3;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class PrimeSpiral2 {
    public static boolean[] sieve;
    public static final int LENGTH = 10_000;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<String[]> inputs = new ArrayList<>();
        String input;
        while ((input = in.readLine()) != null && !input.isEmpty()) {
            inputs.add(input.split(" "));
        }

        sieve = getPrimeNumbers();
        Map<Point, Integer> spiral = new HashMap<>();
        spiral.put(new Point(0, 0), 1);

        boolean addOne = true;
        int numberOfIterations = 1;
        Point currentPoint = new Point(0, 0);
        for (int i = 2; i <= LENGTH; ) {

            for (int j = 0; j < numberOfIterations && i <= LENGTH; j++, i++) {
                if (addOne) {
                    currentPoint.x++;
                } else {
                    currentPoint.x--;
                }

                if (!isPrime(i)) {
                    spiral.put(new Point(currentPoint), i);
                }
            }

            for (int j = 0; j < numberOfIterations && i <= LENGTH; j++, i++) {
                if (addOne) {
                    currentPoint.y++;
                } else {
                    currentPoint.y--;
                }
                if (!isPrime(i)) {
                    spiral.put(new Point(currentPoint), i);
                }
            }

            numberOfIterations++;
            addOne = !addOne;
        }

        for (int i = 0; i < inputs.size(); i++) {
            int result = getShortestPath(spiral, Integer.parseInt(inputs.get(i)[0]), Integer.parseInt(inputs.get(i)[1]));
            System.out.println("Case " + (i + 1) + ": " + (result == -1 ? "impossible" : result));
        }
    }

    private static int getShortestPath(Map<Point, Integer> spiral, int start, int end) {
        if (start == end)
            return 0;

        Map<Integer, Node> q = new HashMap<>();
        spiral.forEach((point, integer) -> q.put(integer, new Node(integer, point, Integer.MAX_VALUE, null)));

        if (surroundedByPrimes(spiral, q.get(start), q.get(end)))
            return -1;

        q.get(start).distance = 0;
        List<Node> s = new ArrayList<>();

        Node currentNode;
        while (!q.isEmpty()) {
            currentNode = getMin(q);

            if (currentNode == null) {
                return -1;
            }

            s.add(currentNode);

            updateNode(q.get(spiral.get(new Point(currentNode.coordinates.x + 1, currentNode.coordinates.y))), currentNode);
            updateNode(q.get(spiral.get(new Point(currentNode.coordinates.x - 1, currentNode.coordinates.y))), currentNode);
            updateNode(q.get(spiral.get(new Point(currentNode.coordinates.x, currentNode.coordinates.y + 1))), currentNode);
            updateNode(q.get(spiral.get(new Point(currentNode.coordinates.x, currentNode.coordinates.y - 1))), currentNode);

            if (currentNode.number == end) {
                break;
            }
        }

        if (s.isEmpty())
            return -1;

        return countPath(s.get(0), s.get(s.size() - 1));
    }

    private static void updateNode(Node n, Node currentNode) {
        if (n != null) {
            if (n.distance > currentNode.distance) {
                n.distance = currentNode.distance + 1;
                n.prevNode = currentNode;
            }
        }
    }

    private static boolean surroundedByPrimes(Map<Point, Integer> spiral, Node start, Node end) {
        if (
                spiral.get(new Point(start.coordinates.x + 1, start.coordinates.y)) == null &&
                        spiral.get(new Point(start.coordinates.x - 1, start.coordinates.y)) == null &&
                        spiral.get(new Point(start.coordinates.x, start.coordinates.y + 1)) == null &&
                        spiral.get(new Point(start.coordinates.x, start.coordinates.y - 1)) == null
        )
            return true;
        if (
                spiral.get(new Point(end.coordinates.x + 1, end.coordinates.y)) == null &&
                        spiral.get(new Point(end.coordinates.x - 1, end.coordinates.y)) == null &&
                        spiral.get(new Point(end.coordinates.x, end.coordinates.y + 1)) == null &&
                        spiral.get(new Point(end.coordinates.x, end.coordinates.y - 1)) == null
        )
            return true;

        return false;
    }

    private static int countPath(Node start, Node end) {
        List<Node> path = new ArrayList<>();

        while (true) {
            if (end == null)
                return -1;
            path.add(end);
            if (end.number == start.number)
                break;
            end = end.prevNode;
        }

        return path.size() - 1;
    }

    private static Node getMin(Map<Integer, Node> q) {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Node> entry : q.entrySet()) {
            if (entry.getValue().distance < min) {
                minIndex = entry.getKey();
                min = entry.getValue().distance;
            }
        }

        return q.remove(minIndex);
    }

    private static class Node {
        public int number;
        public Point coordinates;
        public int distance;
        public Node prevNode;

        public Node(int number, Point coordinates, int distance, Node prevNode) {
            this.number = number;
            this.coordinates = coordinates;
            this.distance = distance;
            this.prevNode = prevNode;
        }
    }

    public static boolean isPrime(int number) {
        return sieve[number];
    }

    private static boolean[] getPrimeNumbers() {
        int max = 10_001;
        boolean[] sieve = new boolean[max];
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sieve[1] = false;

        for (int i = 2; i * i <= max; i++) {
            if (sieve[i]) {
                for (int j = i * i; j < max; j += i)
                    sieve[j] = false;
            }
        }

        return sieve;
    }
}
