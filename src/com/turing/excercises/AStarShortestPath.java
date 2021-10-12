package com.turing.excercises;

import java.util.*;

public class AStarShortestPath {
    // Below arrays detail all four possible movements from a cell
    private static final int[] row = {-1, 0, 0, 1};
    private static final int[] col = {0, -1, 1, 0};

    static class Node implements Comparable<Node> {
        int x;
        int y;

        int gCost;
        int hCost;

        Node parent;

        public int getFCost() {
            return gCost + hCost;
        }

        @Override
        public int compareTo(Node o) {
            return getFCost();
        }
    }

    public static int findShortestAStarPath(final int[][] mat, final int startX, final int startY,
                                      final int endX, final int endY, final boolean printPath) {
        final Queue<Node> open = new PriorityQueue<>();
        final Queue<Node> closed = new PriorityQueue<>();

        final Node start = new Node();
        start.x = startX;
        start.y = startY;
        start.gCost = 0;
        start.hCost = 0;


        open.add(start);
        while (!open.isEmpty()) {
            final Node current = open.poll();
            System.out.println("Called Node | " + current.x + "," + current.y + " | " + current.getFCost());
            closed.add(current);
            if(sameCoordinates(current, endX, endY)) {
                if(printPath) {
                    Node traversedNode = current;
                    while(Objects.nonNull(traversedNode.parent)) {
                        System.out.println("Node | " + traversedNode.x + "," + traversedNode.y);
                        traversedNode = traversedNode.parent;
                    }
                }
                return current.getFCost();
            }

            for(int k=0; k<4; k++){
                int neighbourX = current.x + row[k];
                int neighbourY = current.y + col[k];
                if(isTraversable(mat, neighbourX, neighbourY) &&
                        closed.stream().noneMatch(node -> sameCoordinates(node, neighbourX, neighbourY))) {
                    final Node next = new Node();
                    next.x = neighbourX;
                    next.y = neighbourY;
                    next.gCost = current.gCost + 1;
                    next.hCost = (endX - neighbourX) + (endY - neighbourY);
                    next.parent = current;
                    open.add(next);
                }
            }
        }
        return -1;
    }

    private static boolean isTraversable(final int[][] mat, final int neighbourX, final int neighbourY) {
        return (neighbourX != -1 && neighbourX != mat.length) &&
                (neighbourY != -1 && neighbourY != mat[0].length)
                && mat[neighbourX][neighbourY] == 1;
    }

    private static boolean sameCoordinates(final Node current, final int x, final int y) {
        return current.x == x && current.y == y;
    }
}
