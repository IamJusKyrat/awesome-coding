package com.turing.excercise.string;

import com.turing.excercise.TestResultsHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidParenthesesMemo {

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) return res;
        Set<String> visited = new HashSet<>();
        Set<String> set = new HashSet<>();
        // Find all of possible strings which are valid parentheses
        // Store all of them to set
        // To optimalize, use visited variable for memorizaiton
        dfs(s, set, visited);
        // Finding the max length of string means removing the minimum number of invalid parentheses
        int max = 0;
        for (String str : set) {
            max = Math.max(str.length(), max);
        }
        for (String str : set) {
            if (str.length() == max) res.add(str);
        }
        return res;
    }

    void dfs(String s, Set<String> set, Set<String> visited) {
        if (set.contains(s) || visited.contains(s)) return;
        if (isValid(s)) {
            set.add(s);
            return;
        }
        visited.add(s);
        for (int i = 0 ; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')' || c == '(') {
                String s1 = s.substring(0, i);
                String s2 = s.substring(i + 1);
                dfs(s1 + s2, set, visited);
            }
        }
    }

    boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')'){
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }

    private void run() {
        String testCase1 = "()())()";
        List<String> result1 = List.of("(())()", "()()()");

        String testCase2 = "(a)())()";
        List<String> result2 = List.of("(a())()", "(a)()()");

        String testCase3 = ")(";
        List<String> result3 = List.of("");

        String testCase4 = "x(";
        List<String> result4 = List.of("x");

        TestResultsHelper.verify("1", result1, removeInvalidParentheses(testCase1));
        TestResultsHelper.verify("2", result2, removeInvalidParentheses(testCase2));
        TestResultsHelper.verify("3", result3, removeInvalidParentheses(testCase3));
        TestResultsHelper.verify("4", result4, removeInvalidParentheses(testCase4));


    }

    public static void main(String[] args) {
        new ValidParenthesesMemo().run();
    }

}
