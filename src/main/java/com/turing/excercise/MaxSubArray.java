package com.turing.excercise;

public class MaxSubArray {
    /**
     *  This solution uses the passing windows approach where sum for initial k values are calculated
     *  and then subsequently removing the first and adding the next value we find the sum and the average.
     *
     *  1. Uses one pass.
     * @param nums
     * @param k
     * @return
     */
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
