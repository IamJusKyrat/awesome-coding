package com.turing.excercise.string;

import java.util.Arrays;

/**
 * Category: Medium
 *
 * Question: Given a string s, find the length of the longest substring without repeating characters.
 *
 * Reference: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestNonRepeatingSubstring implements Runnable{

    public int lengthOfLongestSubstring(String s) {
        int[] indexLocations = new int[255];
        Arrays.fill(indexLocations, -1);
        int maxLength = 0, curLength = 0, subStringStartIndex = 0;
        for(int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            int asciiValue = c;
            if(indexLocations[asciiValue] != -1 && subStringStartIndex <= indexLocations[asciiValue]) {
                if(maxLength < curLength) maxLength = curLength;
                curLength = i-indexLocations[asciiValue] - 1;
                subStringStartIndex = indexLocations[asciiValue] + 1;
            }
            curLength++;
            indexLocations[asciiValue] = i;
        }

        if(maxLength < curLength) maxLength = curLength;
        return maxLength;
    }

    @Override
    public void run() {
        String[] testcases = new String[] {"abcabcbb", "bbbbb", "pwwkew", "aab"};
        Arrays.stream(testcases).forEach(s -> System.out.println(lengthOfLongestSubstring(s)));
    }
}
