package com.turing;

import com.turing.excercises.CoinChanger;

public class Main {
    public static void main(String[] args){

        int[] coins = {186,419,83,408};
        int sum = 6249;

        CoinChanger m = new CoinChanger();
        System.out.println("At least " + m.changerSolution1(coins, sum)
                + " coins are required to make a value of " + sum);
    }
}
