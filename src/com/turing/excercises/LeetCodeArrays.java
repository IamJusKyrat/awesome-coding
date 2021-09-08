package com.turing.excercises;

public class LeetCodeArrays {
    /**
     * Kadane's Algorithm:
     * This implementation is a good example of the Dynamic Programming methodology.
     * Extrapolation to find local maxima in a subarray and then compare it to the global maxima to find max subarray sum.
     */
    public static int maxProfit121(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
            System.out.println(maxCur + " " + maxSoFar);
        }
        return maxSoFar;
    }

}
