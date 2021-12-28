package com.turing;

import com.turing.excercise.string.StringToInteger;

public class Main {
    public static void main(String[] args) {
        StringToInteger atoi = new StringToInteger();
        atoi.run();
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
