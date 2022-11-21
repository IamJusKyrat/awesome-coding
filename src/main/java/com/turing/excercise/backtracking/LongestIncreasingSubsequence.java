package com.turing.excercise.backtracking;

import com.turing.excercise.TestResultsHelper;

/**
 * Category: Medium
 *
 * Question:Given an integer array nums, return the length of the longest strictly increasing
 * subsequence.A subsequence is a sequence that can be derived from an array by deleting
 * some or no elements without changing the order of the remaining elements.
 * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 * Reference: https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        //Find all combinations, get the longest one
        int[] memo = new int[nums.length];
        int MIN_SENTINAL = -99999;
        return lis(nums, 0, MIN_SENTINAL, 0, memo);
    }

    private int lis(int[] nums, int currIndex, int prev, int count, int[] memo) {
        if(currIndex >= nums.length || nums[currIndex] < prev) return count;
        if(memo[currIndex] > 0) return memo[currIndex];
        memo[currIndex] =  Math.max(lis(nums, currIndex+1, nums[currIndex], count+1, memo),
                lis(nums, currIndex+1, prev, count, memo));
        return memo[currIndex];
    }

    void run(){
        TestResultsHelper.verify("1", 7, lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
    }

    public static void main(String[] args) {
        new LongestIncreasingSubsequence().run();
    }
}
