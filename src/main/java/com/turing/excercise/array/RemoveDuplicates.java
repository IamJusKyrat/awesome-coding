package com.turing.excercise.array;

import java.util.Arrays;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int i = 0, j;
        for(j = 1; j < nums.length; j++) {
            if(nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    public void run() {
        System.out.println(removeDuplicates(new int[]{1, 1, 2,}));
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }

    public static void main(String[] args) {
        new RemoveDuplicates().run();
    }
}
