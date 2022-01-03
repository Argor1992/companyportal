package week4;

import java.awt.*;
import java.util.List;
import java.util.*;

public class KnightJump {
    public static final List<Movement> MOVEMENTS = List.of(
            new Movement(2, 1),
            new Movement(2, -1),
            new Movement(-2, 1),
            new Movement(-2, -1),
            new Movement(+1, +2),
            new Movement(+1, -2),
            new Movement(-1, +2),
            new Movement(-1, -2)
    );

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int dim = Integer.parseInt(in.nextLine());
        Point positionK = new Point();

        char[][] graph = new char[dim][dim];
        for (int i = 0; i < dim; i++) {
            String input = in.nextLine();
            for (int j = 0; j < input.length(); j++) {
                graph[i][j] = input.charAt(j);

                if (graph[i][j] == 'K') {
                    positionK.x = i;
                    positionK.y = j;
                }
            }
        }

        Point goal = new Point(0, 0);

        System.out.println(getShortestPath(graph, positionK, goal));
    }

    private static int getShortestPath(char[][] graph, Point positionK, Point goal) {
        if (graph[positionK.x][positionK.y] == '#')
            return -1;

        if (positionK == goal)
            return 0;

        Map<Point, Node> q = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                q.put(new Point(i, j), new Node(graph[i][j], new Point(i, j), Integer.MAX_VALUE));
            }
        }

        q.get(positionK).distance = 0;
        List<Node> s = new ArrayList<>();

        Node currentNode;
        while (!q.isEmpty()) {
            currentNode = getMin(q);

            if (currentNode == null) {
                return -1;
            }

            s.add(currentNode);

            if (currentNode.coordinates.equals(goal)) {
                break;
            }

            for (Movement movement : MOVEMENTS) {
                Node node = q.get(new Point(currentNode.coordinates.x + movement.r, currentNode.coordinates.y + movement.c));

                if (node != null && node.value == '.') {
                    if (node.distance > currentNode.distance) {
                        node.distance = currentNode.distance + 1;
                    }
                }
            }
        }

        if (!s.isEmpty() && s.get(s.size()-1).coordinates.equals(goal)) {
            return s.get(s.size()-1).distance;
        }

        return -1;
    }

    private static Node getMin(Map<Point, Node> q) {
        Point minIndex = new Point(-1, -1);
        int min = Integer.MAX_VALUE;

        for (Map.Entry<Point, Node> entry : q.entrySet()) {
            if (entry.getValue().distance < min) {
                minIndex = entry.getKey();
                min = entry.getValue().distance;
            }
        }

        return q.remove(minIndex);
    }

    private static class Movement {
        public int r;
        public int c;

        public Movement(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static class Node {
        public char value;
        public Point coordinates;
        public int distance;

        public Node(char value, Point coordinates, int distance) {
            this.value = value;
            this.coordinates = coordinates;
            this.distance = distance;
        }
    }
}
