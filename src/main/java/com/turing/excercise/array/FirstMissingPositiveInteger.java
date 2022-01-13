package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;
import com.turing.excercise.string.LongestNonRepeatingSubstring;

import java.util.Arrays;

public class FirstMissingPositiveInteger {
    //HashMap of entries
    public int firstMissingPositive(int[] nums) {
        boolean[] mapping = new boolean[nums.length+2]; Arrays.fill(mapping, false);
        //Find the lowest positive integer, if number is > 1 then 1
        //else largest positive integer > 1 where sequence is broken.
        for (int num : nums) {
            if (0 < num && num <= nums.length) {
                mapping[num] = true;
            }
        }

        for(int i = 1; i < mapping.length; i++) {
            if(!mapping[i]) return i;
        }
        return nums.length;
    }

    //In-place replacement if number is encountered means that index is marked as negative, iterate over that array again first positive is value
    public int firstMissingPositiveInPlace(int[] nums) {
        // Base case if 1 does not exist.
        int contains = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == 1) {contains++;break;}

        if (contains == 0) return 1;

        //make all -ve,0 and greater than num of element to 1
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < 1 || nums[i] > nums.length) nums[i] = 1;
        }
        //Find the lowest positive integer, if number is > 1 then 1
        //else largest positive integer > 1 where sequence is broken.
        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            if(val == nums.length) nums[0] = -Math.abs(nums[0]);
            else nums[val] = - Math.abs(nums[val]);
        }

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > 0) return nums[i];
        }
        if (nums[0] > 0) return nums.length;
        return nums.length;
    }

    public void run() {
        TestResultsHelper.verify("1", 3, firstMissingPositiveInPlace(new int[]{1,2,0}));
        TestResultsHelper.verify("2", 2, firstMissingPositiveInPlace(new int[]{3,4,-1,1}));
        TestResultsHelper.verify("3", 1, firstMissingPositiveInPlace(new int[]{7,8,9,11,12}));
        TestResultsHelper.verify("4", 2, firstMissingPositiveInPlace(new int[]{1}));
    }

    public static void main(String[] args) {
        new FirstMissingPositiveInteger().run();
    }
}
