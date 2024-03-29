package com.turing.excercise.graph;

import com.turing.excercise.TestResultsHelper;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class EscapeDungeonAStar {
    // Below arrays detail all four possible movements from a cell
    private static final int[] row = {-1, 0, 0, 1};
    private static final int[] col = {0, -1, 1, 0};

    private static boolean isTraversable(final int[][] mat, final int neighbourX, final int neighbourY) {
        return (neighbourX != -1 && neighbourX != mat.length) &&
                (neighbourY != -1 && neighbourY != mat[0].length)
                && mat[neighbourX][neighbourY] == 1;
    }

    private static boolean sameCoordinates(final Node current, final int x, final int y) {
        return current.x == x && current.y == y;
    }

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

    public int escapeDungeonAStar(final int[][] mat, final int startX, final int startY,
                                         final int endX, final int endY, final boolean printPath) {
        final Queue<Node> open = new PriorityQueue<>();
        final Queue<Node> closed = new PriorityQueue<>();

        final Node start = new Node();
        start.x = startX;
        start.y = startY;
        start.gCost = 0;
        start.hCost = 0;


        open.add(start);
        int nodesCalled = 0;
        while (!open.isEmpty()) {
            final Node current = open.poll();
            nodesCalled += 1;
            //System.out.println("Called Node ("+ nodesCalled +") | " + current.x + "," + current.y + " | " + current.getFCost());
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

    private void run(){
        int[][] testCase1 = { { 1, 0, 0, 1 },
                { 1, 1, 1, 1 },
                { 1, 0, 0, 1 },
                { 1, 0, 1, 1 } };
        TestResultsHelper.verify("1", 7, this.escapeDungeonAStar(testCase1,0,0,3,2, false));
        int[][] testCase2 =
                {
                        {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                        {0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                        {0, 0, 1, 0, 1, 1, 1, 0, 0, 1},
                        {1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
                        {0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
                        {0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
                        {0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                        {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                        {0, 0, 1, 0, 0, 1, 1, 0, 0, 1},
                };

        TestResultsHelper.verify("2", 18, this.escapeDungeonAStar(testCase2,0,0,9,9, false));
    }

    public static void main(String[] args){
        new EscapeDungeonAStar().run();
    }
}
