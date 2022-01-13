package com.turing.excercise.string;

import com.turing.excercise.TestResultsHelper;

import java.util.Arrays;

public class SimplifyPath {
    char DELIM = '/';
    String DELIM_STR = "/";
    String PWD = ".";
    String DIR_UP = "..";
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        boolean wb= false;
        int i = 0;
        while(i < path.length()) {
            if(path.charAt(i) == DELIM && wb == false) {
                wb = true;
            } if(wb == true) {
                StringBuilder sb1 = new StringBuilder();
                while(i < path.length() && path.charAt(i) == DELIM) {i++;continue;}

                while(i < path.length() && path.charAt(i) != DELIM) {
                    sb1.append(path.charAt(i++));
                }
                wb = false;
                if(sb1.toString().equals(PWD)) continue;
                if(sb1.toString().equals(DIR_UP)) {
                    if(sb.lastIndexOf(DELIM_STR) >=0) sb.delete(sb.lastIndexOf(DELIM_STR), sb.length());
                    continue;
                }
                sb.append(DELIM+sb1.toString());
            }
        }
        return sb.length() >=2  && sb.charAt(sb.length() - 1) == DELIM ? sb.substring(0, sb.length()-1): sb.toString();
    }

    public void run() {
        TestResultsHelper.verify("1", "/home", simplifyPath("/home/"));
        TestResultsHelper.verify("2", "/", simplifyPath("/../"));
        TestResultsHelper.verify("3", "/c", simplifyPath("/a/./b/../../c/"));
    }

    public static void main(String[] args) {
        new SimplifyPath().run();
    }
}
