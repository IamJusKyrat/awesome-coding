package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

import java.util.PriorityQueue;

public class FindMaxProfit {
    public static int maxProfit(int k, int[] prices) {
        //While price inflection to decrease  - sell
        //Do not buy until you get to lowest point.
        //Keep holding till price is going up.
        //Find all such periods. Get the k max profit periods for answer
        //If no of operations is exhausted.

        PriorityQueue<Integer> maxProfits = new PriorityQueue<Integer>((o1, o2) -> {
            if(o2 > o1) return 1;
            else if(o1 > o2) return -1;
            return 0;
        });
        int start = prices[0];
        int end = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < end) {
                maxProfits.add(end - start);
                start = prices[i];
                end = prices[i];
            } else {
                end = prices[i];
            }
        }

        if((end - start) > 0) maxProfits.add(end - start);
        int totalProfit = 0;

        for (int i = 0; i < k; i++) {
            if (maxProfits.peek() != null) {
                totalProfit += maxProfits.poll();
            }
        }

        return totalProfit;
    }

    private static void run() {
        TestResultsHelper.verify("1", 7,
                FindMaxProfit.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    public static void main(String[] args) {
        FindMaxProfit.run();
    }
}
