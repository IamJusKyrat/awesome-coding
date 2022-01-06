package com.turing.excercise;

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
}
