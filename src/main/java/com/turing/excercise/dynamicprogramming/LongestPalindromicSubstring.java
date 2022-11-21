package com.turing.excercise.dynamicprogramming;

import com.turing.excercise.tree.FindPsuedoPalindromicPaths;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        //Modify string to add mirror items in between;
        char[] arr = new char[s.length() * 2 - 1];
        int[] palinRadii = new int[s.length() * 2 - 1];
        for (int i = 0; i < s.length(); i++) {
            arr[2 * i] = s.charAt(i);
            if(i != s.length() - 1) arr[2 * i + 1] = '|';
        }
        int centre = 0;
        int radius = 0;

        while (centre < arr.length) {
            while (centre - (radius + 1) >= 0
                    && centre + (radius + 1) < arr.length
                    && arr[centre - (radius + 1)] == arr[centre + (radius + 1)])
                radius = radius + 1;

            palinRadii[centre] = radius;
            int oldCentre = centre;
            int oldRadius = radius;

            centre = centre + 1;
            radius = 0;

            while (centre <= oldCentre + oldRadius) {
                int mirrorCentre = oldCentre - (centre - oldCentre);
                int maxMirrorRadius = oldCentre + (oldRadius - centre);
                if (palinRadii[mirrorCentre] < maxMirrorRadius) {
                    //palindrome edge is within bounds of the old radius thus smaller
                    palinRadii[centre] = palinRadii[mirrorCentre];
                    centre = centre + 1;
                } else if (palinRadii[mirrorCentre] > maxMirrorRadius) {
                    //palindrome edge is beyond the borders which means next elements are not
                    //matched, thus the longest palindrome can be maxRadius from here to edge
                    palinRadii[centre] = maxMirrorRadius;
                    centre = centre + 1;
                } else {
                    //Everything uptil the border of the radius is matched
                    //art matching from there onwards
                    radius = maxMirrorRadius;
                    break;
                }
            }
        }

        return longestPalindrome(arr, palinRadii);
    }

    private String longestPalindrome(char[] arr, int[] palinRadii) {
        int maxRadii = 0;
        int maxCentre = 0;
        int j = 0;
        while (j < palinRadii.length) {
            if (palinRadii[j] > maxRadii) {
                maxRadii = palinRadii[j];
                maxCentre = j;
            }
            j++;
        }

        StringBuilder s = new StringBuilder();

        for (int i = maxCentre - maxRadii; i <= maxCentre + maxRadii; i++) {
            if (arr[i] != '|') s.append(arr[i]);
        }

        return s.toString();
    }

    public void run() {
        System.out.println(longestPalindrome("bb"));
    }

    public static void main(String[] args) {
        new LongestPalindromicSubstring().run();
    }
}
