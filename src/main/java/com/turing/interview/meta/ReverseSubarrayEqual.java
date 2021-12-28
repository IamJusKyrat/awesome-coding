package com.turing.interview.meta;

import java.util.Stack;

public class ReverseSubarrayEqual {
    boolean areTheyEqual(int[] a, int[] b) {
        // Write your code here
        if (a.length != b.length) return false;
        boolean isMatch = true;
        int i = 0, j = 0;
        Stack<Integer> stack = new Stack<>();
        while (i < a.length && j < b.length) {
            if(!isMatch) break;
            if (a[i] == b[j]) {
                i++;j++;
                while (!stack.isEmpty()) {
                    if (a[i] == stack.pop()) {i++;}
                    else {isMatch = false; break;}
                }
            } else {
                stack.push(b[j]); j++;
            }
        }

        if(isMatch) {
            while (!stack.isEmpty()) {
                if (a[i] == stack.pop()) {i++;}
                else {isMatch = false;break;}
            }
        }
        return isMatch;
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    void check(boolean expected, boolean output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            System.out.print(expected);
            System.out.print(" Your output: ");
            System.out.print(output);
            System.out.println();
        }
        test_case_number++;
    }

    public void run() {
        int[] array_a_1 = {1, 2, 3, 4};
        int[] array_b_1 = {1, 4, 3, 2};
        boolean expected_1 = true;
        boolean output_1 = areTheyEqual(array_a_1, array_b_1);
        check(expected_1, output_1);

        int[] array_a_2 = {1, 2, 3, 4};
        int[] array_b_2 = {1, 4, 3, 3};
        boolean expected_2 = false;
        boolean output_2 = areTheyEqual(array_a_2, array_b_2);
        check(expected_2, output_2);
        // Add your own test cases here

    }

    public static void main(String[] args) {
        new ReverseSubarrayEqual().run();
    }
}
