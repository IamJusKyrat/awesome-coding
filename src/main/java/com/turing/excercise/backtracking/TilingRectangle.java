package com.turing.excercise.backtracking;

import com.turing.excercise.TestResultsHelper;

/**
 * Category: Hard
 *
 * Question:Given a rectangle of size n x m, return the minimum number of integer-sided squares that tile the rectangle.
 *
 * https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/
 */
public class TilingRectangle {
    public int tilingRectangle(int n, int m) {
        //There is no clear way. It is permutations that need to be solved.
        //We need to reduce permutations to make computation faster.
        //1. Largest square remaining will be max length remaining vs max width.
        //2. Positioning of squares is important. (Is it?)
        //3. Remaining area determines the size of the square.
        return till(n * m, n, m, Math.min(m, n), 0);
    }

    private int till(int area, int length, int width, int maxSquareSide, int numSquares) {
        if (area < 0) return Integer.MAX_VALUE;
        if (area == 0) {
            return numSquares;
        }
        int minCount = Integer.MAX_VALUE;
        for (int i = maxSquareSide; i >= 1; i--) {
            int remArea = area - (i * i);
            minCount = Math.min(minCount, till(remArea, length, width, getMaxLength(remArea, length, width), numSquares + 1));
        }
        return minCount;
    }

    int getMaxLength(int remArea, int length, int width) {
        return (int) Math.min(length, Math.min(width, Math.sqrt(remArea)));
    }

    void run(){
        //TestResultsHelper.verify("1", 3, tilingRectangle(2,3));
        TestResultsHelper.verify("2", 5, tilingRectangle(5,8));
        //TestResultsHelper.verify("1", 6, tilingRectangle(11,13));
    }

    public static void main(String[] args) {
        new TilingRectangle().run();
    }
}
