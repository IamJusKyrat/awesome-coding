package com.turing.excercise.string;

import java.util.Arrays;

/**
 * Category: Medium
 *
 * Question: Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * Read in and ignore any leading whitespace.
 * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
 * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
 * Return the integer as the final result.
 * Note:
 *
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 *
 *
 * Reference: https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToInteger implements Runnable{

    static final int[] POWER_OF_TEN = {1,10,100,1000,10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    public int myAtoi(String s) {
        int MAX_INTEGER = 2147483647, MIN_INTEGER = -2147483648;
        int readPointer = 0;
        boolean isNegative = false;
        char[] numberArr = new char[10]; int numLength = 0;

        while((readPointer < s.length()) && numLength < 10) {
            char value = s.charAt(readPointer);
            int asciiValue = value;

            if((numLength > 0) && (asciiValue < 48 || asciiValue > 57)) break;
            ++readPointer;
            if(asciiValue == 32 || asciiValue == 43) { continue; }
            else if(asciiValue == 45) { isNegative = true; }
            else if(48 <= asciiValue && asciiValue <= 57) {numberArr[numLength] = value; numLength++;}

        }

        int number = 0;
        for(int i = 0; i < numLength; i++) {
            int radixValue = Character.getNumericValue(numberArr[numLength - (i+1)]);
            number +=  radixValue * POWER_OF_TEN[i];
        }

        if(isNegative) number *= -1;

        if(number >= MAX_INTEGER) return MAX_INTEGER;
        if(number <= MIN_INTEGER) return MIN_INTEGER;
        return number;
    }

    @Override
    public void run() {
        String[] testcases = new String[] {"42", "   -42", "4193 with words", "words and 987"};
        Arrays.stream(testcases).forEach(s -> System.out.println(myAtoi(s)));
    }
}
