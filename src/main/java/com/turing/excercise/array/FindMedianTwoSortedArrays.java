package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

/**
 * Category: Hard
 *
 * Question: Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 * Reference: https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class FindMedianTwoSortedArrays {
    //When you want median of two sorted arrays
    //If we merge these two sorted arrays into one array and then get the median
    //Time: O(m+n), Space: O(m+n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalSize = nums1.length + nums2.length;
        int k = 0;
        int[] newArr = new int[totalSize];
        for (int i = 0, j = 0; i < nums1.length || j < nums2.length; ) {
            int nums1Val = i >= nums1.length ? Integer.MAX_VALUE : nums1[i];
            int nums2Val = j >= nums2.length ? Integer.MAX_VALUE : nums2[j];
            if (nums1Val <= nums2Val) {
                newArr[k] = nums1Val;
                i++;
            } else {
                newArr[k] = nums2Val;
                j++;
            }
            k++;
        }

        if (totalSize % 2 == 0) {
            return ((double) newArr[totalSize / 2 - 1] + newArr[totalSize / 2]) / 2;
        } else {
            return newArr[totalSize / 2];
        }
    }

    private void run() {
        TestResultsHelper.verify("1", 2.0, findMedianSortedArrays(new int[]{1,2}, new int[]{3}));
        TestResultsHelper.verify("2", 2.5, findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }

    public static void main(String[] args) {
        new FindMedianTwoSortedArrays().run();
    }
}
