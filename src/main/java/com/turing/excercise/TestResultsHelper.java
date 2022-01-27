package com.turing.excercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TestResultsHelper {
    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    private static char rightTick = '\u2713';
    private static char wrongTick = '\u2717';

    static void printIntegerArray(int[] arr) {
        int len = arr.length;
        System.out.print("[");
        for(int i = 0; i < len; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.print("]");
    }

    public static void verify(String test_case, int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }

        if (result) {
            System.out.println(rightTick + " Test #" + test_case);
        } else {
            System.out.print(wrongTick + " Test #" + test_case + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
    }

    public static <T> void verify(String test_case, List<T> expected, List<T> output) {
        int expected_size = expected.size();
        int output_size = output.size();
        boolean result;
        if (expected_size != output_size) {
            result = false;
        }
        List<T> copyExpected = new ArrayList<>(expected);
        copyExpected.removeAll(output);
        result = copyExpected.size() == 0;
        if (result) {
            System.out.println(rightTick + " Test #" + test_case);
        } else {
            System.out.print(wrongTick + " Test #" + test_case + ": Expected ");
            System.out.println(expected.toString());
            System.out.print(" Your output: ");
            System.out.println(output.toString());
            System.out.println();
        }
    }

    public static void verify(String test_case, int expected, int output) {
        boolean result = expected == output;
        if (result) {
            System.out.println(rightTick + " Test #" + test_case);
        } else {
            System.out.print(wrongTick + " Test #" + test_case + ": Expected " + expected);
            System.out.print(" Your output: " + output);
            System.out.println();
        }
    }

    public static void verify(String test_case, String expected, String output) {
        boolean result = expected.equals(output);
        if (result) {
            System.out.println(rightTick + " Test #" + test_case);
        } else {
            System.out.print(wrongTick + " Test #" + test_case + ": Expected " + expected);
            System.out.print(" Your output: " + output);
            System.out.println();
        }
    }

    public static void verify(String test_case, boolean expected, boolean output) {
        boolean result = expected == output;
        if (result) {
            System.out.println(rightTick + " Test #" + test_case);
        } else {
            System.out.print(wrongTick + " Test #" + test_case + ": Expected " + expected);
            System.out.print(" Your output: " + output);
            System.out.println();
        }
    }

    public static void  printMemoMatrix(int[][] memo) {
        for(int i = 0; i < memo.length; i++) {
            System.out.println(Arrays.toString(memo[0]));
        }
    }

    public static void  printMemoMatrix(Set<String>[][] memo) {
        for(int i = 0; i < memo.length; i++) {
            System.out.println(Arrays.toString(memo[0]));
        }
    }
}
