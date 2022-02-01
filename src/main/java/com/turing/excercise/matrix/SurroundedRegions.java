package com.turing.excercise.matrix;

/**
 * Category: Medium
 * <p>
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * Reference: https://leetcode.com/problems/surrounded-regions/
 */
public class SurroundedRegions {
    private final int[] dx = new int[]{0, 1, 0, -1};
    private final int[] dy = new int[]{1, 0, -1, 0};

    public void solve(char[][] board) {
        //For each row+col combination we need to check if all four directions
        //we will have two loops running
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                if (board[i][j] == 'O' && isCaptured(board, i, j, visited))
                    paint(board, i, j);
            }
        }
    }

    boolean isCaptured(char[][] board, int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        if(board[i][j] == 'X') return true;
        boolean solution = true;
        for (int k = 0; k < dx.length; k++) {
            int ni = i + dx[k];
            int nj = j + dy[k];
            if(ni < 0 || ni == board.length || nj < 0 || nj == board[i].length) return false;
            if(!visited[ni][nj]) solution = solution && isCaptured(board, ni, nj, visited);
        }

        return solution;
    }

    void paint(char[][] board, int i, int j) {
        board[i][j] = 'X';
        for (int k = 0; k < dx.length; k++) {
            int ni = i + dx[k];
            int nj = j + dy[k];
            if (ni < board.length && ni >= 0
                    && nj < board[i].length && nj >= 0
                    && board[ni][nj] == 'O') {
                paint(board, ni, nj);
            }
        }
    }

    private void run() {
        char[][] board_ts1 = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        this.solve(board_ts1);
        printBoard(board_ts1);


        char[][] board_ts2 = new char[][]{
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
        };

        this.solve(board_ts2);
        printBoard(board_ts2);

    }

    private void printBoard(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]).append(",");
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        new SurroundedRegions().run();
    }
}
