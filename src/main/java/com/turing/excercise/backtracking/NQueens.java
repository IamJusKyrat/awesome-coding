package com.turing.excercise.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    List<Integer> rightDiag; //r-c
    List<Integer> leftDiag; //r+c
    List<Integer> cols;
    List<List<String>> solutions;

    public List<List<String>> solveNQueens(int n) {
        solutions = new ArrayList<>();
        rightDiag = new ArrayList<>(); //r-c
        leftDiag = new ArrayList<>(); //r+c
        cols = new ArrayList<>();
        int[][] board = new int[n][n];
        recurse(0, board, n, 0);
        return solutions;
    }

    void recurse(int r, int[][] board, int n, int queens) {
        if(queens==n) {solutions.add(display(board, n));}
        if(r >= n) return;
        for(int c = 0; c < n; c++) {
            if(isSafe(r,c, board, rightDiag, leftDiag, cols)) {
                addQueen(r,c, board, rightDiag, leftDiag, cols);
                recurse(r+1, board, n, queens+1);
                removeQueen(r,c, board, rightDiag, leftDiag, cols);
            }
        }
    }

    void addQueen(int r, int c, int[][]board, List<Integer> rightDiag, List<Integer> leftDiag, List<Integer> cols) {
        board[r][c] = 1;
        cols.add(c);
        leftDiag.add(r+c);
        rightDiag.add(r-c);
    }

    void removeQueen(int r, int c, int[][]board, List<Integer> rightDiag, List<Integer> leftDiag, List<Integer> cols) {
        board[r][c] = 0;
        cols.remove(cols.size() - 1);
        leftDiag.remove(leftDiag.size() - 1);
        rightDiag.remove(rightDiag.size() - 1);
    }

    boolean isSafe(int r, int c, int[][]board, List<Integer> rightDiag, List<Integer> leftDiag, List<Integer> cols) {
        if(cols.contains(c)) return false;
        if(rightDiag.contains(r-c)) return false;
        if(leftDiag.contains(r+c)) return false;
        return true;
    }

    List<String> display(int[][] board, int n) {
        List<String> boardDis = new ArrayList<>();
        for(int r = 0; r < n; r++) {
            StringBuilder row = new StringBuilder();
            for(int c = 0; c < n; c++) {
                if(board[r][c] == 0) row.append(".");
                else row.append("Q");
            }
            boardDis.add(row.toString());
        }
        return boardDis;
    }

    void run(){
        //[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        solveNQueens(4);
    }

    public static void main(String[] args) {
        new NQueens().run();
    }
}
