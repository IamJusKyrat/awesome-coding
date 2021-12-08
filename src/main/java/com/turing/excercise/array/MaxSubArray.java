package com.turing.excercise.array;

/**
 * Category: Easy
 *
 * Question: Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * A subarray is a contiguous part of an array.
 *
 * Reference: https://leetcode.com/problems/maximum-subarray/
 */
public class MaxSubArray {
    /**
     *  This solution uses the sliding windows approach where sum for initial k values are calculated
     *  and then subsequently removing the first and adding the next value we find the sum and the average.
     *
     *  1. Uses one pass.
     * @param nums
     * @param k
     * @return
     */

    //Complexity: Time: O(n), Space: O(1)
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0, i = 0, n = nums.length;
        double maxAvg = 0;

        for(i = 0;  i < k; i++) {
            sum += nums[i];
        }

        maxAvg = ((double) sum)/k;
        if(i == n) {
            return maxAvg;
        }

        for(i = 0; i < n-k; i++) {
            sum = sum - nums[i] + nums[i+k];
            double avg = ((double) sum)/k;
            if(avg > maxAvg) {
                maxAvg = avg;
            }
        }
        return maxAvg;
    }
}
