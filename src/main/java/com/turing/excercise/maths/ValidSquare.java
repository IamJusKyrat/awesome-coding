package com.turing.excercise.maths;

import com.turing.interview.meta.RotationalCipher;

import java.util.Arrays;

/**
 * Category: Medium
 *
 * Question: Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.
 * The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 *
 * https://leetcode.com/problems/valid-square/
 */
public class ValidSquare {

    public double dist(int[] p1, int[] p2) {
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = {p1,p2,p3,p4};
        Arrays.sort(p, (l1, l2) -> l2[0] == l1[0] ? l1[1]-l2[1]: l1[0]-l2[0]);
        return dist(p[0], p[1]) != 0 &&
                dist(p[0], p[1]) == dist(p[1], p[3]) &&
                dist(p[1], p[3]) == dist(p[3], p[2]) &&
                dist(p[3], p[2]) == dist(p[2], p[0]) &&
                dist(p[0], p[3]) == dist(p[1], p[2]);
    }

    public void run() {
        boolean output_1 = validSquare(new int[]{0,0},new int[]{1,1}, new int[]{1,0}, new int[]{0,1});
        check("true", Boolean.toString(output_1));

        boolean output_2 = validSquare(new int[]{0,0},new int[]{1,1}, new int[]{1,0}, new int[]{0,12});
        check("false", Boolean.toString(output_2));

        boolean output_3 = validSquare(new int[]{7,2},new int[]{9,5}, new int[]{12,3}, new int[]{10,0});
        check("true", Boolean.toString(output_3));

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new ValidSquare().run();
    }


    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;
    void check(String expected, String output) {
        boolean result = (expected.equals(output));
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printString(expected);
            System.out.print(" Your output: ");
            printString(output);
            System.out.println();
        }
        test_case_number++;
    }
    void printString(String str) {
        System.out.print("[\"" + str + "\"]");
    }
}
