package com.turing.concept.caching.dictonary;

import java.util.HashMap;
import java.util.Map;

class TNode {
    Map<Character, TNode> children;
    boolean eow;

    public TNode(boolean eow) {
        children = new HashMap<>();
        this.eow = eow;
    }
}

public class WordDictionary {

    TNode base;
    public WordDictionary() {
        base = new TNode(false);
    }

    public void addWord(String word) {
        if(word.length() == 0) return;
        char[] letters = word.toCharArray();
        TNode trav = base;
        for(int i=0; i< letters.length; i++) {
            char c = letters[i];
            boolean eow = i == letters.length -1;
            if(!trav.children.containsKey(c)) {
                trav.children.put(c, new TNode(eow));
            }
            trav = trav.children.get(c);
        }
    }

    public boolean search(String word) {
        isFound = false; find(word, base);
        return isFound;
    }

    boolean isFound;
    private void find(String word, TNode trav) {
        if(isFound) return;
        if(word.equals("")) {
            isFound = trav.eow;
            return;
        }
        char letter = word.charAt(0);
        if(letter == '.') {
            for(TNode child: trav.children.values())
                find(word.substring(1), child);
        } else {
            TNode next = trav.children.get(letter);
            if(next == null) return;
            find(word.substring(1), next);
        }
    }
}

class TestWDR {
    public static void main(String[] args) {
        WordDictionary w = new WordDictionary();
        w.addWord("at"); w.addWord("and"); w.addWord("an"); w.addWord("add"); w.addWord("bat");
        System.out.println(w.search(".at"));
    }
}
