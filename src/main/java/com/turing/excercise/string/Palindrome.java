package com.turing.excercise.string;

import com.turing.excercise.TestResultsHelper;
import com.turing.excercise.array.FindPeakElement;

public class Palindrome {
    public boolean isPalindrome(String s) {
        int i = 0, j= s.length()-1;
        while(j > i) {
            while (!isAlphanumeric(s.charAt(i))) i++;
            while (!isAlphanumeric(s.charAt(j))) j--;
            if(!validate(s,i,j)) return false;
            i++; j--;
        }
        return true;
    }

    boolean validate(String s, int i, int j) {
        char ci = s.charAt(i);
        char cj = s.charAt(j);
        if(isDigit(ci)) {
            if(!isDigit(cj)) return false;
            return (ci -'0') == (cj - '0');
        } else {
            int vali = isCapital(ci) ? ci - 'A' : ci - 'a';
            int valj = isCapital(cj) ? cj - 'A' : cj - 'a';
            return vali == valj;
        }
    }

    boolean isAlphanumeric(char a) {
        return ('A' <= a && a <= 'Z') || ('a' <= a && a <= 'z') || ('0' <= a && a <= '9');
    }

    boolean isCapital(char a) {
        return 'A' <= a && a <= 'Z';
    }

    boolean isDigit(char a) {
        return '0' <= a && a <= '9';
    }

    public void run() {
        TestResultsHelper.verify("1", true, isPalindrome("A man, a plan, a canal: Panama"));
        TestResultsHelper.verify("2", false, isPalindrome("Race a car"));
    }

    public static void main(String[] args) {
        new Palindrome().run();
    }
}
