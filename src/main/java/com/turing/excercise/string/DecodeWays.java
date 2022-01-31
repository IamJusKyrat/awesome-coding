package com.turing.excercise.string;

import com.turing.excercise.TestResultsHelper;

/**
 * Category: Medium
 *
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of
 * the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 * Reference: https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {
    private static int maxNum = 26, minNum = 1;
    public int numDecodings(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        return encodings(s);
    }

    private int encodings(String rem) {
        if(rem == null || rem.length() == 0) return 1;
        if(rem.charAt(0) == '0') return 0;
        int validValues = 0;
        //Where we select first number
        int curr = Integer.parseInt(rem.substring(0,1));
        if(curr >= minNum) {
            validValues += encodings(rem.substring(1));
        }

        //Where we select first two numbers
        if(rem.length() > 1) {
            int curr2 = Integer.parseInt(rem.substring(0,2));
            if(curr2 <= maxNum) {
                validValues += encodings(rem.substring(2));
            }
        }
        return validValues;
    }

    private void run(){
        //TestResultsHelper.verify("1", 2, numDecodings("12"));
        //TestResultsHelper.verify("2", 3, numDecodings("226"));
        //TestResultsHelper.verify("3", 0, numDecodings("06"));
        TestResultsHelper.verify("4", 1, numDecodings("2101"));
    }

    public static void main(String[] args) {
        new DecodeWays().run();
    }
}
