package com.turing.excercise.string;

import com.turing.excercise.TestResultsHelper;

import java.util.*;

public class ValidParentheses {
    private List<String> res;
    private int[][]memo;
    private int maxLength;
    public List<String> removeInvalidParentheses(String s) {
        res= new ArrayList<>();
        int n=s.length();
        if(n==0){
            res.add("");
            return res;
        }
        int leftCount=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='(') leftCount++;
        }
        memo=new int[n][leftCount+1];
        maxLength=0;
        backtrack(s,0,"",0);
        TestResultsHelper.printMemoMatrix(memo);
        return res;
    }

    private void backtrack(String s, int index, String prefix, int left){
        if(index==s.length()){
            if(left==0){
                if((res.size()==0 || maxLength==prefix.length())&&!res.contains(prefix)) {
                    if(maxLength==0) maxLength=prefix.length();
                    res.add(prefix);
                }
            }
            return;
        }

        if(prefix.length()<memo[index][left]) return;
        memo[index][left]=prefix.length();
        char c=s.charAt(index);
        if(c==')'){
            //can choose to take the right if there are open left parentheses
            if(left>0){
                //first try to choose to take the right so that the length for final result is maximized
                backtrack(s,index+1,prefix+c,left-1);
            }
            backtrack(s,index+1,prefix,left);
        }
        else if(c=='('){
            //choose to either take the left or not
            //again try first to take the left so that the length of final result is maximized
            backtrack(s,index+1,prefix+c,left+1);
            backtrack(s,index+1,prefix,left);
        }
        else backtrack(s,index+1,prefix+c,left);
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
        new ValidParentheses().run();
    }
}
