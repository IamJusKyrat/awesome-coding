package com.turing.excercise.string;

import com.turing.excercise.TestResultsHelper;

public class FindDifference {
    public char findTheDifference(String s, String t) {
        if(s.length() == 0) return t.charAt(0);
        int[] counts = new int[26];
        for(int i=0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }

        for(int i=0; i < t.length(); i++) {
            counts[t.charAt(i) - 'a']--;
        }

        char res = '\0';
        for(int k = 0; k < 26; k++) {
            if(counts[k] < 0) res = (char) ('a' + k);
        }

        return res;
    }

    private void run(){
        TestResultsHelper.verify("1", "e", String.valueOf(findTheDifference("abcd", "abcde")));
    }

    public static void main(String[] args) {
        new FindDifference().run();
    }
}
