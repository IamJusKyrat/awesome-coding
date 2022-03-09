package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

/**
 * Category: Hard
 *
 * Question: Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 * Reference: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class MinimumRotatedSortedArray {
    public int findMin(int[] nums) {
        //There is still an inflection point
        //The answer to left is equal to number you had now then this maybe the lowest number
        //or there maybe a lower number to your left.
        //if equal you run both sides and do a min
        if(nums.length == 0) return 0;
        int low = 0; int high = nums.length - 1;

        while(high > low) {
            int mid = (high-low)/2 + low;

            if(nums[mid] < nums[high]) high = mid;
            else if(nums[mid] > nums[high]) low = mid+1;
            else high -= 1;
        }
        return nums[low];
    }

    private void run() {
        TestResultsHelper.verify("1", 0, findMin(new int[]{2,2,2,0,1}));
        TestResultsHelper.verify("2", 1, findMin(new int[]{1,3,5}));
    }

    public static void main(String[] args) {
        new MinimumRotatedSortedArray().run();
    }
}
