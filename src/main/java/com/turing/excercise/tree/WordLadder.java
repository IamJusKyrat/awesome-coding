package com.turing.excercise.tree;

import com.turing.excercise.TestResultsHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;


public class WordLadder {
    //This is DFS, this ensures a path but not necessarily the smallest.
    static class DFS {
        public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
            //First check if the endWord is in the list
            //Run search for next word which can be found, add it to the list if endWord is found the break return count;
            boolean endWordExists = false;
            for (String word : wordList) {
                if (word.equals(endWord)) endWordExists = true;
            }
            return !endWordExists ? 0 : findPath(beginWord, endWord, wordList, 0, new ArrayList<>());
        }

        //Time Complexity: (n!*m) Space complexity: (n)
        private static int findPath(String srcW, String tgtW, List<String> wordList, int count, List<String> visited) {
            if (srcW.equals(tgtW)) return count;
            for (String word : wordList) {
                if (!visited.contains(word) && editDistance(srcW, word) == 1) {
                    visited.add(word);
                    int result = findPath(word, tgtW, wordList, count + 1, visited);
                    visited.remove(word);
                    if (result != -1) return result;
                }
            }
            return -1;
        }

        private static int editDistance(String src, String tgt) {
            int editDistance = 0;
            for (int i = 0; i < src.length(); i++) {
                if (src.charAt(i) != tgt.charAt(i)) editDistance++;
            }
            return editDistance;
        }
    }

    //Smallest Path with Time
    //Time Complexity: O(M2*N)
    //Space Complexity: O(M2*N)
    static class IntelligentBFS {
        public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

            // Since all words are of same length.
            int L = beginWord.length();

            // Dictionary to hold combination of words that can be formed,
            // from any given word. By changing one letter at a time.
            Map<String, List<String>> allComboDict = new HashMap<>();

            //Created a reverse index list like
            //("h*t", ["hit", "hot"])
            //("*og") ["hog", "log", "cog"]
            wordList.forEach( word -> {
                for (int i = 0; i < L; i++) {
                    // Key is the generic word
                    // Value is a list of words which have the same intermediate generic word.
                    String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                    List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                    transformations.add(word);
                    allComboDict.put(newWord, transformations);
                }
            });

            // Queue for BFS
            Queue<Pair<String, Integer>> Q = new LinkedList<>();
            Q.add(new ImmutablePair<>(beginWord, 1));

            // Visited to make sure we don't repeat processing same word.
            Map<String, Boolean> visited = new HashMap<>();
            visited.put(beginWord, true);

            while (!Q.isEmpty()) {
                Pair<String, Integer> node = Q.remove();
                String word = node.getKey();
                int level = node.getValue();
                for (int i = 0; i < L; i++) {

                    // Intermediate words for current word
                    String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                    // Next states are all the words which share the same intermediate state.
                    for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                        // If at any point if we find what we are looking for
                        // i.e. the end word - we can return with the answer.
                        if (adjacentWord.equals(endWord)) {
                            return level + 1;
                        }
                        // Otherwise, add it to the BFS Queue. Also mark it visited
                        if (!visited.containsKey(adjacentWord)) {
                            visited.put(adjacentWord, true);
                            Q.add(new ImmutablePair<>(adjacentWord, level + 1));
                        }
                    }
                }
            }

            return 0;
        }
    }

    private void run() {
        TestResultsHelper.verify("1", 5, IntelligentBFS.ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log","cog")));
    }

    public static void main(String[] args) {
        new WordLadder().run();
    }
}
