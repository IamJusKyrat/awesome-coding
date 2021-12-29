package com.turing.excercise.string;

import com.turing.interview.meta.LargestTripleMultiplication;

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
    public int myAtoi(String s) {
        int readPointer = 0; int number = 0;
        int sign = 0;

        while((readPointer < s.length())) {
            char value = s.charAt(readPointer);
            int asciiValue = value;
            ++readPointer;
            if(asciiValue == 32) {continue;}
            else if(asciiValue == 43) {sign = 1;}
            else if(asciiValue == 45) { sign = -1; }
            else if(48 <= asciiValue && asciiValue <= 57) {
                if(sign == 0) sign = 1;
                int digit = value - '0';
                if ((number > Integer.MAX_VALUE / 10)
                        || (number == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE %10)) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                number = number*10 + digit;
            } else {break;}
        }
        return sign*number;
    }



    @Override
    public void run() {
        String[] testcases = new String[] {"42", "   -42", "4193 with words", "words and 987"};
        Arrays.stream(testcases).forEach(s -> System.out.println(myAtoi(s)));
    }

    public static void main(String[] args) {
        new StringToInteger().run();
    }
}
