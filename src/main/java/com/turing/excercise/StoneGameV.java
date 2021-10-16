package com.turing.excercise;

import java.util.Arrays;

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
