package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreSentencesSimilar {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;
        Map<String, Integer> index = new HashMap<>();
        int count = 0;
        DSU dsu = new DSU(2 * pairs.size());
        for (List<String> pair: pairs) {
            for (String p: pair) if (!index.containsKey(p)) {
                index.put(p, count++);
            }
            dsu.union(index.get(pair.get(0)), index.get(pair.get(1)));
        }

        for (int i = 0; i < words1.length; ++i) {
            String w1 = words1[i], w2 = words2[i];
            if (w1.equals(w2)) continue;
            if (!index.containsKey(w1) || !index.containsKey(w2) ||
                    dsu.find(index.get(w1)) != dsu.find(index.get(w2)))
                return false;
        }
        return true;
    }

    static class DSU {
        int[] parent;
        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; ++i)
                parent[i] = i;
        }
        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }


    public void run() {
        //["great","acting","skills"]
        //["fine","drama","talent"]
        //[["great","fine"],["drama","acting"],["skills","talent"]]
        String[] testcase1_1 = new String[]{"I","love","leetcode"};
        String[] testcase1_2 = new String[]{"I","love","onepiece"};
        List<List<String>> testcase1_pairs = List.of(List.of("leetcode","platform"), List.of("manga","onepiece"),
                List.of("platform","anime"), List.of("anime","manga"));
        int target1 = 8;
        int target5 = 2;
        TestResultsHelper.verify("1", true, this.areSentencesSimilarTwo(testcase1_1, testcase1_2, testcase1_pairs));
    }

    public static void main(String[] args) {
        new AreSentencesSimilar().run();
    }
}
