package com.turing.excercise.array;

import java.util.Arrays;

/**
 * Category: Hard
 *
 * Question: There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
 * In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row), then Bob calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row. If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.
 * The game ends when there is only one stone remaining. Alice's is initially zero.
 * Return the maximum score that Alice can obtain.
 *
 * Reference: https://leetcode.com/problems/stone-game-v/
 */
public class StoneGameV {
    private static int aliceSum = 0;
    public static void main(String[] args) {
        int[] input = {6,2,3,4,5,5};
        int[] input2 = {7,7,7,7,7,7,7};
        int response = stoneGameV(input2);
        System.out.println(response);
    }

    public static int stoneGameV(int[] stoneValue) {
        if(stoneValue.length <= 1) {
            return aliceSum;
        }

        int[] nextStoneValue;
        int homeIndex = 0;
        int endIndex = stoneValue.length - 1;
        int homeCount = 0, endCount = 0;
        do {
            if(homeCount <= endCount) {
                homeCount += stoneValue[homeIndex];
                homeIndex++;
            } else {
                endCount += stoneValue[endIndex];
                endIndex--;
            }
            System.out.println("Iteration => " + homeIndex + " : " + endIndex);
            System.out.println(homeCount + ":" + endCount);
        } while (homeIndex <= endIndex);

        if(homeCount > endCount) {
            aliceSum += endCount;
            nextStoneValue = Arrays.copyOfRange(stoneValue, endIndex, stoneValue.length-1);
        } else {
            aliceSum += homeCount;
            nextStoneValue = Arrays.copyOfRange(stoneValue, 0, homeIndex);
        }
        stoneGameV(nextStoneValue);
        return aliceSum;
    }
}
