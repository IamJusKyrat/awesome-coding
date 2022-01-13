package com.turing.interview.meta;

/**
 * Element Swapping
 * Given a sequence of n integers arr, determine the lexicographically smallest sequence which may be obtained from it after performing at most k element swaps, each involving a pair of consecutive elements in the sequence.
 * Note: A list x is lexicographically smaller than a different equal-length list y if and only if, for the earliest index at which the two lists differ, x's element at that index is smaller than y's element at that index.
 */
public class ElementSwapping {

    // Add any helper functions you may need here
    private boolean lexicallySmaller(int a, int b) {
        return String.valueOf(a).compareTo(String.valueOf(b)) < 0;
    }

    int[] findMinArray(int[] arr, int k) {
        // Write your code here
        //1. Iterate over the string using 2 pointers.
        //2. Replace the K-th smallest element to the start.
        //3. If the smallest number gets to the start without using the full swaps then make next smallest element swap to the start.
        if(arr.length == 0 || arr.length == 1) return arr;
        int i = 0;
        int remainingSwaps = k;
        for(i = 0; i < arr.length; i++) {
            if(remainingSwaps == 0) {
                break;
            }
            int minValueIndex = i, minValue = arr[i], j;
            for(j = i+1; j <= remainingSwaps + i && j < arr.length; j++) {
                if(arr[j]< minValue) {minValue = arr[j]; minValueIndex = j;}
            }
            if(minValueIndex != i) {
                swap(arr, minValueIndex, i);
                remainingSwaps = remainingSwaps - minValueIndex + i;
            }
        }
        return arr;
    }

    private void swap(int[] arr, int src, int target) {
        int minValue = arr[src];
        int cpValue = arr[target];
        for(int i = target; i < src; i++) {
            int tmp = arr[i+1];
            arr[i+1] = cpValue;
            cpValue = tmp;
        }

        arr[target] = minValue;
    }


    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;
    void check(int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
        test_case_number++;
    }
    void printIntegerArray(int[] arr) {
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

    public void run() {
        int n_1 = 3, k_1 = 2;
        int[] arr_1 = {5, 3, 1};
        int[] expected_1 = {1, 5, 3};
        int[] output_1 = findMinArray(arr_1,k_1);
        check(expected_1, output_1);

        int n_2 = 5, k_2 = 3;
        int[] arr_2 = {8, 9, 11, 2, 1};
        int[] expected_2 = {2, 8, 9, 11, 1};
        int[] output_2 = findMinArray(arr_2,k_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new ElementSwapping().run();
    }
}
