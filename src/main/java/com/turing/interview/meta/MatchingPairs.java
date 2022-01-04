package com.turing.interview.meta;

import java.util.HashSet;
import java.util.Set;

public class MatchingPairs {
    // Add any helper functions you may need here

    private boolean isSwappable(char sSwap, char tSwap, char sChar, char tChar) {
        return sSwap == tChar && tSwap == sChar;
    }

    //Should use stack?
    int matchingPairsSecondary(String s, String t) {
        return 0;
    }

    int matchingPairs(String s, String t) {
        //1. Run through the sequence to find the max matching subsequence.
        //2. Discount the first mismatch as it can be rectified by a swap.
        //3. On the second mismatch check if the first is swappable, if so continue to the end.
        char sSwap = '\0', tSwap = '\0';
        int currentSub = 0, maxSub = 0;
        int swapIndex = -1, swappedIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i), tChar = t.charAt(i);
            if (sChar != tChar) {
                if (isSwappable(sSwap, tSwap, sChar, tChar)) {
                    if (swappedIndex != -1) {
                        maxSub = currentSub;
                        currentSub = currentSub - (swapIndex + 1);
                        swapIndex = swappedIndex;
                        swappedIndex = i;
                    } else {
                        swappedIndex = i;
                    }
                    currentSub++;
                } else {
                    if (swappedIndex != -1) {
                        maxSub = currentSub;
                        currentSub = currentSub - (swappedIndex);
                        swapIndex = i;
                        swappedIndex = -1;
                    } else {
                        swapIndex = i;
                        currentSub = 0;
                    }
                }
                sSwap = sChar;tSwap = tChar;
            } else {
                currentSub++;
            }

        }

        return swappedIndex != -1 && swapIndex != -1 ? Math.max(maxSub, currentSub) : currentSub - 2;
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {
        String s_1 = "abcde";
        String t_1 = "adcbe";
        int expected_1 = 5;
        int output_1 = matchingPairs(s_1, t_1);
        check(expected_1, output_1);

        String s_2 = "abcd";
        String t_2 = "abcd";
        int expected_2 = 2;
        int output_2 = matchingPairs(s_2, t_2);
        check(expected_2, output_2);

        String s_3 = "adcbefghdjklm";
        String t_3 = "abcdefghbjklm";
        int expected_3 = 11;
        int output_3 = matchingPairs(s_3, t_3);
        check(expected_3, output_3);

        String s_4 = "adcbefghnjklmi";
        String t_4 = "abcdefghijklmn";
        int expected_4 = 10;
        int output_4 = matchingPairs(s_4, t_4);
        check(expected_4, output_4);

        String s_5 = "abc";
        String t_5 = "xyz";
        int expected_5 = 0;
        int output_5 = matchingPairs(s_5, t_5);
        check(expected_5, output_5);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new MatchingPairs().run();
    }
}
