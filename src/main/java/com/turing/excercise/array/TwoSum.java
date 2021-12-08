package com.turing.excercise.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Category: Easy
 *
 * Question: Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Reference: https://leetcode.com/problems/two-sum/
 */
public class TwoSum {
    public void run() {
        final int[] arrayInputs = new int[]{11,3,-5,-12,7,13};
        final int targetSum = -9;
        System.out.println(Arrays.toString(SortSolution.solve(arrayInputs, targetSum)));
    }

    public static class SortSolution {
        /**
         * This solution uses merge sort hence changes the initial structure of the array.
         *  1. This solution will not work if we have to give out indexes as indexes are changed.
         *  2. This can only work if we need to find weather or not there exists a single solution to the problem,or if we output the values
         *
         * @param nums
         * @param target
         * @return
         */
        private static int[] solve(int[] nums, int target) {
            mergeSort(nums, 0, nums.length - 1);
            int i = 0,j = nums.length -1;
            while(j > 0 && i < nums.length - 1){
                final int actualSum = nums[i] + nums[j];
                if(target == actualSum){
                    return new int[]{i,j};
                } else if(target < actualSum){
                    j--;
                } else {
                    i++;
                }
            }
            return new int[]{};
        }

        private static void merge(int[] nums, int low, int middle, int high) {
            int i, j, k;
            final int n1 = middle - low + 1;
            final int n2 = high - middle;

            final int[] lowNums = new int[n1];
            final int[] highNums = new int[n2];

            for (i = 0; i < n1; i++) {
                lowNums[i] = nums[low + i];
            }

            for (j = 0; j < n2; j++) {
                highNums[j] = nums[middle + 1 + j];
            }

            i = 0;j = 0;k = low;
            while (i < n1 && j < n2) {
                if (lowNums[i] <= highNums[j]) {
                    nums[k] = lowNums[i];
                    i++;
                } else {
                    nums[k] = highNums[j];
                    j++;
                }
                k++;
            }

            while (i < n1) {
                nums[k] = lowNums[i];
                i++;k++;
            }
            while (j < n2) {
                nums[k] = highNums[j];
                j++;k++;
            }
        }

        static void mergeSort(int[] nums, int low, int high) {
            if (low < high) {
                // Same as (l+r)/2, but avoids overflow for
                // large l and h
                int middle = low + (high - low) / 2;

                // Sort first and second halves
                mergeSort(nums, low, middle);
                mergeSort(nums, middle + 1, high);

                merge(nums, low, middle, high);
            }
        }
    }

    public static class MapSolution{
        /**
         * This solution only checks the difference at each point passing once over the entire array,
         * if complement value is found it will output the response.
         * @param nums
         * @param target
         * @return
         */
        private static int[] solve(int[] nums, int target) {
            Map<Integer, Integer> prevMap = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int diff = target - nums[i];

                if (prevMap.containsKey(diff)) {
                    return new int[]{ prevMap.get(diff), i};
                }
                prevMap.put(nums[i], i);
            }
            return new int[0]; // Guranteed solution, no need for return


        }
    }
}
