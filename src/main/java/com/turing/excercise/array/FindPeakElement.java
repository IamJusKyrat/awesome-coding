package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

import java.util.Arrays;

public class FindPeakElement {
    int index;
    public int findPeakElement(int[] nums) {
        if(nums.length == 1) return 0;
        index = -1;
        binarySearch(nums, 0, nums.length-1);
        return index;
    }

    void binarySearch(int[] nums, int low, int high) {
        if(high >= low) {
            if(index != -1) return;
            int mid = (high-low)/2+low;
            if(mid == 0) {
                if(nums[mid] > nums[mid+1]) {index = mid; return;}
            } else if(mid == nums.length-1) {
                if(nums[mid] > nums[mid-1]) {index = mid; return;}
            } else {
                int found = 0;
                if(nums[mid] > nums[mid-1]) found++;
                if(nums[mid] > nums[mid+1]) found++;
                if(found == 2) {index = mid; return;}
            }
            binarySearch(nums, low, mid-1);
            binarySearch(nums, mid+1,high);
        }
    }

    public void run() {
        TestResultsHelper.verify("1", 2, findPeakElement(new int[]{1, 2, 3, 1}));
        TestResultsHelper.verify("2", 5, findPeakElement(new int[]{1,2,1,3,5,6,4}));
        TestResultsHelper.verify("3", 1, findPeakElement(new int[]{1,2}));
        TestResultsHelper.verify("4", 0, findPeakElement(new int[]{2,1}));
    }

    public static void main(String[] args) {
        new FindPeakElement().run();
    }
}
