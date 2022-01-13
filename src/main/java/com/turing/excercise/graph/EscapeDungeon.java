package com.turing.excercise.graph;

import com.turing.excercise.TestResultsHelper;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class EscapeDungeon {
    // Below arrays detail all four possible movements from a cell
    private static final int[] dRow = {-1, 0, 0, 1};
    private static final int[] dCol = {0, -1, 1, 0};

    // Function to check if it is possible to go to position (row, col)
    // from the current position. The function returns false if (row, col)
    // is not a valid position or has a value 0 or already visited.
    private boolean isValid(int[][] mat, boolean[][] visited, int row, int col) {
        return (row >= 0) && (row < mat.length) && (col >= 0) && (col < mat[0].length)
                && mat[row][col] == 1 && !visited[row][col];
    }

    // Find the shortest possible route in a matrix `mat` from source
    // cell (i, j) to destination cell (x, y)
    public int escapeDungeon(int[][] mat, int i, int j, int x, int y) {
        // base case: invalid input
        if (mat == null || mat.length == 0 || mat[i][j] == 0 || mat[x][y] == 0) {
            return -1;
        }

        // `M × N` matrix
        int M = mat.length;
        int N = mat[0].length;

        // construct a matrix to keep track of visited cells
        boolean[][] visited = new boolean[M][N];
        Stack<MatrixNode> nodesStacked = new Stack<>();

        Queue<MatrixNode> q = new ArrayDeque<>();

        // mark the source cell as visited and enqueue the source node
        visited[i][j] = true;
        q.add(new MatrixNode(i, j, 0));

        int min_dist = Integer.MAX_VALUE;

        // loop till queue is empty
        while (!q.isEmpty()) {
            // dequeue front node and process it
            MatrixNode node = q.poll();
            nodesStacked.add(node);
            // (i, j) represents a current cell, and `dist` stores its
            // minimum distance from the source
            i = node.x;
            j = node.y;
            int dist = node.dist;

            //System.out.println("Distance: " + node.dist + " | " + i + "," + j);
            // if the destination is found, update `min_dist` and stop
            if (i == x && j == y) {
                min_dist = dist;
                break;
            }

            // check for all four possible movements from the current cell
            // and enqueue each valid movement
            for (int k = 0; k < 4; k++) {
                // check if it is possible to go to position
                // (i + row[k], j + col[k]) from current position
                if (isValid(mat, visited, i + dRow[k], j + dCol[k])) {
                    // mark next cell as visited and enqueue it
                    visited[i + dRow[k]][j + dCol[k]] = true;
                    q.add(new MatrixNode(i + dRow[k], j + dCol[k], dist + 1));
                }
            }
        }

        if (min_dist != Integer.MAX_VALUE) {
            while (!nodesStacked.isEmpty()) {
                nodesStacked.pop();
            }
            return min_dist;
        }
        return -1;
    }

    private void run(){
        int[][] testCase1 = { { 1, 0, 0, 1 },
                         { 1, 1, 1, 1 },
                         { 1, 0, 0, 1 },
                         { 1, 0, 1, 1 } };
        TestResultsHelper.verify("1", 7, this.escapeDungeon(testCase1,0,0,3,2));
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

        TestResultsHelper.verify("2", 18, this.escapeDungeon(testCase2,0,0,9,9));
    }

    public static void main(String[] args){
        new EscapeDungeon().run();
    }
}
