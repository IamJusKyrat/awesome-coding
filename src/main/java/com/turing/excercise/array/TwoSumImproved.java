package com.turing.excercise.array;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSumImproved implements Runnable {
    public int[] twoSum(int[] nums, int target) {
        //Remainder value and index
        HashMap<Integer, Integer> remainders = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (remainders.containsKey(nums[i])) {
                return new int[]{remainders.get(nums[i]), i};
            } else {
                int remainder = target - nums[i];
                remainders.put(remainder, i);
            }
        }
        return new int[]{0, 0};
    }

    public void run() {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 19}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{3,2,4}, 6)));
    }

    public static void main(String[] args) {
        new TwoSumImproved().run();
    }

}
