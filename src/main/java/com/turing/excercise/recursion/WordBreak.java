package com.turing.excercise.recursion;

import com.turing.excercise.TestResultsHelper;

import java.util.*;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        final Map<Character, List<String>> beginWords = new HashMap<>(wordDict.size());
        wordDict.forEach(word -> {
            char beginWord = word.charAt(0);
            final List<String> listWords = beginWords.getOrDefault(beginWord, new ArrayList<>());
            listWords.add(word);
            beginWords.put(beginWord, listWords);
        });
        return breakWord(s, beginWords);
    }

    private boolean breakWord(String s, Map<Character, List<String>> beginWords) {
        if(s.length() == 0) return true;
        char firstChar = s.charAt(0);
        if(!beginWords.containsKey(firstChar)) return false;
        boolean isBreakWordPossible = false;
        final List<String> listWords = beginWords.get(firstChar);
        for (final String word : listWords) {
            if (s.startsWith(word)) {
                if(word.length() == s.length()) isBreakWordPossible = true;
                else isBreakWordPossible = breakWord(s.substring(word.length()), beginWords);
                if (isBreakWordPossible) break;
            }
        }
        return isBreakWordPossible;
    }

    public void run() {
        TestResultsHelper.verify("1", true, wordBreak("leetcode", List.of("leet", "code")));
    }

    public static void main(String[] args) {
        new WordBreak().run();
    }
}
