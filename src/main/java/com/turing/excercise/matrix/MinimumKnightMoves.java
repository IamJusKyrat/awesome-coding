package com.turing.excercise.matrix;

import com.turing.excercise.TestResultsHelper;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Category: Medium
 *
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.
 *
 * Reference: https://leetcode.com/problems/minimum-knight-moves/
 */
public class MinimumKnightMoves {
    //D,R,U,L
    private final int[] dirX = new int[]{0,1,0,-1};
    private final int[] dirY = new int[]{-1,0,1,0};

    public int minKnightMovesInefficient(int x, int y) {
        //BackTracking Solution
        return knightMoves(0,0,x,y,0);
    }


    //0,0,2,1,0
    private int knightMoves(int fromX, int fromY, int toX, int toY, int count) {
        if(fromX == toX && fromY == toY)  return count;
        int distCurr = dist(fromX, fromY, toX, toY);
        int minCount = Integer.MAX_VALUE;
        for(int i = 0; i < dirX.length; i++) {
            int[][] moves = new int[2][];
            fillKnightMoves(moves, fromX, fromY, i);
            for (int[] move : moves) {
                if (dist(move[0], move[1], toX, toY) <= distCurr) {
                    int result = knightMoves(move[0], move[1], toX, toY, count + 1);
                    if (result > 0) minCount = Math.min(result, minCount);
                }
            }
        }
        return minCount;
    }

    private void fillKnightMoves(int[][] knightMoves, int fromX, int fromY, int dirI) {
        int newFromX = fromX + 2*dirX[dirI];
        int newFromY = fromY + 2*dirY[dirI];

        if(fromX == newFromX) {
            knightMoves[0] = new int[] {newFromX - 1, newFromY};
            knightMoves[1] = new int[] {newFromX + 1, newFromY};
        } else {
            knightMoves[0] = new int[] {newFromX, newFromY - 1};
            knightMoves[1] = new int[] {newFromX, newFromY + 1};
        }
    }

    //Should I calculate manhattan distance to limit where the knight can go
    private int dist(int fromX, int fromY, int toX, int toY) {
        return Math.abs(toX-fromX) + Math.abs(toY-fromY);
    }

    /*************************************************** Solution ******************************************/
    public int minKnightMoves(int x, int y) {
        // the offsets in the eight directions
        int[][] offsets = {{1, 2}, {2, 1}, {2, -1}, {1, -2},
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

        // - Rather than using the inefficient HashSet, we use the bitmap
        //     otherwise we would run out of time for the test cases.
        // - We create a bitmap that is sufficient to cover all the possible
        //     inputs, according to the description of the problem.
        boolean[][] visited = new boolean[607][607];

        Deque<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{0, 0});
        int steps = 0;

        while (queue.size() > 0) {
            int currLevelSize = queue.size();
            // iterate through the current level
            for (int i = 0; i < currLevelSize; i++) {
                int[] curr = queue.removeFirst();
                if (curr[0] == x && curr[1] == y) {
                    return steps;
                }

                for (int[] offset : offsets) {
                    int[] next = new int[]{curr[0] + offset[0], curr[1] + offset[1]};
                    // align the coordinate to the bitmap
                    if (!visited[next[0] + 302][next[1] + 302]) {
                        visited[next[0] + 302][next[1] + 302] = true;
                        queue.addLast(next);
                    }
                }
            }
            steps++;
        }
        // move on to the next level
        return steps;
    }

    private void run() {
        TestResultsHelper.verify("1", 1, minKnightMoves(2,1));
        TestResultsHelper.verify("2", 4, minKnightMoves(5,5));
        TestResultsHelper.verify("3", 3, minKnightMoves(0,1));
    }


    public static void main(String[] args) {
        new MinimumKnightMoves().run();
    }
}
