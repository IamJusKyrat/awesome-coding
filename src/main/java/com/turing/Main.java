package com.turing;

import com.turing.excercise.string.LongestNonRepeatingSubstring;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] testcases = new int[] {5, 8};
        Arrays.stream(testcases).forEach(val -> System.out.println(Main.fib(val)));
        Arrays.stream(testcases).forEach(val -> System.out.println(Main.slowFib(val)));
    }

    private static int slowFib(int value) {
        if(value == 1) return 1;
        return slowFib(value-1) + slowFib(value-2);
    }

    private static long fib(int value) {
        int[] memo = new int[value + 1];
        memo[0] = memo[1] = 1;
        for(int i = 2; i <= value; i++) {
            memo[i] = memo[i-1]+ memo[i-2];
        }
        return memo[value];
    }
}
