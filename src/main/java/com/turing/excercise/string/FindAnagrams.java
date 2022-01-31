package com.turing.excercise.string;

import com.turing.excercise.TestResultsHelper;
import com.turing.excercise.tree.FindAverage;

import java.util.*;

/**
 * Category: Medium
 *
 * Question: Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Reference: https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        //anagram means that we can have elements in any order we just need to meet the criteria that
        //the number of elements with thier respective counts present in string p is match with a contigous string in s

        if(s.length() < p.length()) return new ArrayList<>();

        //We initialize maps with similar number of characters as p beacuse the window size will always be equal to p
        Map<Character, Integer> pc = new HashMap<>();
        Map<Character, Integer> sc = new HashMap<>();
        for(int i = 0;i< p.length(); i++) {
            pc.put(p.charAt(i), pc.getOrDefault(p.charAt(i), 0) + 1);
            sc.put(s.charAt(i), sc.getOrDefault(s.charAt(i), 0) + 1);
        }
        Set<Integer> indexes = new HashSet<>();

        //Initializing left and right pointers to move the window
        int i = 0, j = p.length();

        while(j < s.length()) {
            //Check if maps are equals
            if(mapsEqual(sc,pc)) indexes.add(i);
            //Adding the next character
            char c = s.charAt(j);
            sc.put(c, sc.getOrDefault(c, 0) + 1);

            //Removing the prev one
            char d = s.charAt(i);
            int count = sc.get(d) - 1;
            if(count == 0) sc.remove(d);
            else sc.put(d, count);
            j++;i++;
        }
        //Checking the last one as we have not check when the string ends
        if(mapsEqual(sc,pc)) indexes.add(i);
        return new ArrayList<>(indexes);
    }

    private boolean mapsEqual(Map<Character, Integer> one, Map<Character, Integer> two) {
        if(one.size() != two.size()) return false;
        for(char a : one.keySet()) {
            if(!two.containsKey(a) || !Objects.equals(two.get(a), one.get(a))) return false;
        }
        return true;
    }

    private void run() {
        String ts1_base = "cbaebabacd";
        String ts1_test = "abc";

        TestResultsHelper.verify("1", List.of(0,6), findAnagrams(ts1_base, ts1_test));
        TestResultsHelper.verify("2", List.of(0,1,2), findAnagrams("abab", "ab"));
    }

    public static void main(String[] args) {
        new FindAnagrams().run();
    }
}
