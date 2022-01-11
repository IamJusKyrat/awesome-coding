package com.turing.excercise.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: Write test cases.
 */
public class PairAnagrams {
    String SEPARATOR = "_";
    String TERMINATOR = "|";
    int DISTINCT_CHAR_COUNT = 26;
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> mapping = new HashMap<>();
        for(String str: strs) {
            int[] occur = new int[DISTINCT_CHAR_COUNT];
            for(char c: str.toCharArray()) {
                int val = c - 'a';
                occur[val] = occur[val] + 1;
            }
            String hash = hash(occur);
            List<String> list = mapping.containsKey(hash) ? mapping.get(hash): new ArrayList<>();
            list.add(str);
            mapping.put(hash, list);
        }
        return new ArrayList(mapping.values());
    }

    String hash(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< arr.length - 1; i++) {
            if(arr[i] != 0) {sb.append(i).append(SEPARATOR).append(arr[i]).append(TERMINATOR);}
        }
        return sb.toString();
    }
}
