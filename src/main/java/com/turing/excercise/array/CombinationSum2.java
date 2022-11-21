package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

import java.util.*;

public class CombinationSum2 {
    Map<String, List<Integer>> solutions;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //We try to calculate for each candidate if the sum is achievable
        //For can = {2,3,5} e.g. - 7  (2,2,3), (5,2), (2,5), (3,2,2)
        Arrays.sort(candidates);
        solutions = new HashMap<>();
        count(target, 0, candidates, new ArrayList<>());
        return new ArrayList<>(solutions.values());
    }

    void count(int rem, int i, int[] cands, List<Integer> solution) {
        if(rem == 0) {
            addToSolutions(new ArrayList<>(solution));
            return;
        }
        if(i>= cands.length || rem < 0) return;

        //Include the current number
        solution.add(cands[i]);
        count(rem-cands[i], i+1, cands, solution);
        solution.remove(solution.size() - 1);

        //Exclude current number
        count(rem, i+1, cands, solution);
    }

    void addToSolutions(List<Integer> solution) {
        StringBuilder sb = new StringBuilder();
        for(int item: solution) {
            sb.append(item).append(",");
        }
        solutions.put(sb.toString(), solution);
    }

    void run() {
        int[] ts1_candidates = new int[]{2,5,2,1,2};
        int ts1_sum = 5;
        //List.of(List.of(1,2,2), List.of(5))
        System.out.println(combinationSum2(ts1_candidates,ts1_sum));
    }

    public static void main(String[] args) {
        new CombinationSum2().run();
    }
}
