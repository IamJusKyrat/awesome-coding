package com.turing.excercise.recursion;

import com.turing.excercise.TestResultsHelper;
import com.turing.excercise.string.RomanToInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PossibleLetters {
    List<String> mappings = List.of("abc", "def", "ghi", "jlk","mno", "pqrs", "tuv", "wxyz");
    List<String> words = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        findWords(digits, 0, "");
        return words;
    }

    public void findWords(String digits, int i, String word) {
        if(i == digits.length()) {
            words.add(word);
            return;
        }
        String str = mappings.get((digits.charAt(i++)-'0')-2);
        for(char c: str.toCharArray()) {
            findWords(digits,i, word+c);
        }
    }

    public void run() {
        String testcase1 = "23", testcase2 = "2", testcase3 = "";
        List<String> result1 = List.of("ad","ae","af","bd","be","bf","cd","ce","cf"), result2 = List.of("a","b","c"),
                result3 = Collections.emptyList();
        TestResultsHelper.verify("1", result1, letterCombinations(testcase1));
        TestResultsHelper.verify("2", result2, letterCombinations(testcase2));
        TestResultsHelper.verify("3", result3, letterCombinations(testcase3));
    }

    public static void main(String[] args) {
        new PossibleLetters().run();
    }
}
