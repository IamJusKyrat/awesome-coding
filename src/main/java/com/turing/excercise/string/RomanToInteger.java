package com.turing.excercise.string;

import java.util.Arrays;
import java.util.HashMap;

public class RomanToInteger implements Runnable {

    class RomanItem {
        int value;
        int index;

        public RomanItem(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public int romanToInt(String s) {
        HashMap<Character, RomanItem> romanNumerals = new HashMap<>();
        romanNumerals.put('I', new RomanItem(1, 1));
        romanNumerals.put('V', new RomanItem(5, 2));
        romanNumerals.put('X', new RomanItem(10, 3));
        romanNumerals.put('L', new RomanItem(50, 4));
        romanNumerals.put('C', new RomanItem(100, 5));
        romanNumerals.put('D', new RomanItem(500, 6));
        romanNumerals.put('M', new RomanItem(1000, 7));

        RomanItem prev = romanNumerals.get('I');
        char[] str = s.toCharArray();
        int number = 0;
        for(int i = str.length-1; i >= 0 ; i--) {
            char key = str[i];
            RomanItem current = romanNumerals.get(key);
            if (current.index >= prev.index) {
                number += current.value;
            } else {
                number -= current.value;
            }
            prev = current;
        }
        return number;
    }

    @Override
    public void run() {
        String[] testcases = new String[] {"III", "MCMXCIV"};
        Arrays.stream(testcases).forEach(s -> System.out.println(romanToInt(s)));
    }

    public static void main(String[] args) {
        new RomanToInteger().run();
    }
}
