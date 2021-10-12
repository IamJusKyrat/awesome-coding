package com.turing.excercises;

public class LeetCodeSorting {
    public static int[] smallerNumbersThanCurrent1365(int[] nums) {
        int[] buckets = new int[101];
        for (int i = 0; i < nums.length; i++) {
            buckets[nums[i]]++;
        }

        for (int j = 1; j <= 100; j++) {
            buckets[j] += buckets[j - 1];
        }

        for (int k = 0; k < nums.length; k++) {
            int pos = nums[k];
            nums[k] = pos == 0 ? 0 : buckets[pos - 1];
        }
        return nums;
    }
}
