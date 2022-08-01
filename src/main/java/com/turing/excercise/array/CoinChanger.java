package com.turing.excercise.array;

import java.util.Arrays;

/**
 * Category: Medium
 *
 * Question: You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Reference: https://leetcode.com/problems/coin-change/
 */
public class CoinChanger {

    private static class coinChangeElaborate {
        public int changerSolution1(int[] coins, int sum) {

            int totalCoins = coins.length;

            // Creating array which stores sub-problems' solutions
            double[][] arr = new double[totalCoins + 1][sum + 1];

            // Initialising first row with +ve infinity
            for (int j = 0; j <= sum; j++) {
                arr[0][j] = Double.POSITIVE_INFINITY;
            }

            // Initialising first column with 0
            for (int i = 1; i <= totalCoins; i++) {
                arr[i][0] = 0;
            }

            // Implementing the recursive solution
            for (int i = 1; i <= totalCoins; i++) {
                for (int j = 1; j <= sum; j++) {
                    if (coins[i - 1] <= j)
                        arr[i][j] = min(1 + arr[i][j - coins[i - 1]], arr[i - 1][j]);
                    else
                        arr[i][j] = arr[i - 1][j];
                }
            }

            return (int) arr[totalCoins][sum];
        }

        // Helper function for the recursive solution.
        double min(double a, double b) {
            return Math.min(a, b);
        }

        // Helper function for the recursive solution.
        int min(int a, int b) {
            if (a < 0) return b;
            else if (b < 0) return a;
            else return Math.min(a, b);
        }

        public int changerSolution2(int[] coins, int amount) {
            Arrays.sort(coins);
            int totalCoins = coins.length;

            // Creating array which stores subproblems' solutions
            Integer[][] cache = new Integer[totalCoins][amount];

            if (amount < 1) {
                return -1;
            }
            int count = subProblem(totalCoins - 1, amount, coins, cache);
            if (count == Integer.MAX_VALUE || count == Integer.MIN_VALUE) {
                return -1;
            }
            return count;
        }

        private int subProblem(int coinCount, int sumRemaining, int[] coins, Integer[][] cache) {
            int choiceTake = 0, choiceLeave;

            if (cache[coinCount][sumRemaining - 1] != null) {
                return cache[coinCount][sumRemaining - 1];
            }

            int coinValue = coins[coinCount];

            //Computing the lowest number of coins needed of the current denomination
            if (coinValue > sumRemaining) {
                choiceTake = Integer.MAX_VALUE;
            } else if (coinValue == sumRemaining) {
                choiceTake = 1;
            } else if (coinValue < sumRemaining) {
                choiceTake = 1 + subProblem(coinCount, sumRemaining - coinValue, coins, cache);
            }

            //Computing lowest number of coins needed if not using the current denomination
            if (coinCount == 0) {
                choiceLeave = Integer.MAX_VALUE;
            } else {
                choiceLeave = subProblem(coinCount - 1, sumRemaining, coins, cache);
            }

            int optimalValue = min(choiceTake, choiceLeave);
            cache[coinCount][sumRemaining - 1] = optimalValue;
            return optimalValue;
        }
    }

    private static class CoinChangeFirst {
        public static int coinChange(int[] coins, int amount) {
            if (amount < 1) return 0;
            int response = coinChange(coins, amount, new int[amount]);
            System.out.println(response);
            return response;
        }

        private static int coinChange(int[] coins, int rem, int[] count) {
            if (rem < 0) return -1;
            if (rem == 0) return 0;
            if (count[rem - 1] != 0) return count[rem - 1];
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int res = coinChange(coins, rem - coin, count);
                if (res >= 0 && res < min)
                    min = 1 + res;
            }
            count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return count[rem - 1];
        }
    }

    private static class CoinChangeFU {
        public static int coinChange(int[] coins, int amount) {
            int[] memo = new int[amount+1];
            memo[0] = 0;
            //for each coin we can initiate the memo as 1 as we know that is the minimum case.
            return coinChange(coins, amount, memo);
        }



        private static int coinChange(int[] coins, int amount, int[] memo) {
            System.out.print(amount + " , ");
            if(amount < 0) return -1;
            if(memo[amount] != -1) return memo[amount];
            int minCount = Integer.MAX_VALUE;
            for(int i = coins.length - 1; i >= 0; i--) {
                int coin = coins[i];
                int currCount = coinChange(coins, amount-coin, memo);
                if(currCount != -1 && currCount < minCount) minCount = currCount + 1;
            }
            if(minCount != Integer.MAX_VALUE) {
                memo[amount] = minCount;
            }
            return memo[amount];
        }
    }

    private static void run() {
        CoinChangeFU.coinChange(new int[]{186,419,83,408}, 6249);
    }

    public static void main(String[] args) {
        CoinChanger.run();
    }
}
