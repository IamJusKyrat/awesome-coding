package com.turing.excercise.graph;

import com.turing.excercise.TestResultsHelper;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class WordSearch {
    int[] dx = new int[]{1, 0, -1, 0};
    int[] dy = new int[]{0, -1, 0, 1};

    boolean isValid;

    public boolean exist(char[][] board, String word) {
        //find all starting points by traversing through the board
        //if not founf return false;
        // else we can do bfs on board for word from these starting points
        //if visited do not visit again

        //Add a base case for if board is empty or the word is empty
        isValid = false;
        char[] letters = word.toCharArray();
        int m = board.length;
        if (m == 0 || letters.length == 0) return false;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (letters[0] == board[i][j]) {
                    boolean[][] visited = new boolean[m][n];
                    Set<String> path = new HashSet<>();
                    bfs(board, i, j, 0, word, "", path);
                    if (isValid) return true;
                }
            }
        }
        return false;
    }

    BitSet bitSet = new BitSet(10);

    /* 0,0,0,cdba*/
    void bfs(char[][] board, int i, int j, int index, String word, String newWord, Set<String> path) {
        if(isValid) return;
        if (board[i][j] == word.charAt(index)) {
            String next  = newWord + word.charAt(index);
            path.add(i+"#"+j);
            if (index == word.length() - 1 && word.equals(next)) {
                isValid = true;
                return;
            }
            for (int dir = 0; dir < dx.length; dir++) {
                int newi = i + dx[dir], newj = j + dy[dir];
                if ((newi < 0 || newi >= board.length) || (newj < 0 || newj >= board[0].length)
                        || path.contains(newi+"#"+newj)) continue;
                bfs(board, newi, newj, index + 1, word, next, path);
                path.remove(newi+"#"+newj);
            }
        }
    }

    private void run() {
        String ts1_input2 = "cdba";
        char[][] ts1_input1 = new char[][] {
                {'a','b'},
                {'c','d'}
        };
        String ts2_input2 = "ABCB";
        char[][] ts2_input1 = new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };

        String ts3_input2 = "ABCESEEEFS";
        char[][] ts3_input1 = new char[][] {
                {'A','B','C','E'},
                {'S','F','E','S'},
                {'A','D','E','E'}
        };

        TestResultsHelper.verify("1", true, exist(ts1_input1,ts1_input2));
        TestResultsHelper.verify("2", false, exist(ts2_input1,ts2_input2));
        TestResultsHelper.verify("3", true, exist(ts3_input1,ts3_input2));

    }

    public static void main(String[] args) {
        new WordSearch().run();
    }
}
