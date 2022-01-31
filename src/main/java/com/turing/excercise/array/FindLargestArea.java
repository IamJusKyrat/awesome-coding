package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

import java.util.Stack;

/**
 * Category: Hard
 *
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 *
 * Reference: https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class FindLargestArea {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i <= n; i++){
            int currHeight = i == n ? 0 : heights[i];
            // check if currHeight becomes greater than height[top] element of stack. we do a push because it's an increasing sequence
            // otherwise we do pop and find area, so for that we write a while loop
            while(!st.isEmpty() && currHeight < heights[st.peek()]){
                int top = st.pop(); // current element on which we are working
                // now we need width & area
                int width = st.isEmpty() ? i : i - st.peek() - 1; // width differ, if stack is empty or not empty after we pop the element
                int area = heights[top] * width; // current height * width
                maxArea = Math.max(area, maxArea);
            }
            // if it doesn't enter in while loop, it means it's an increasing sequence & we need to push index
            st.push(i);
        }
        return maxArea;
    }

    private void run() {
        int[] testCase_1 = new int[]{2,1,5,6,2,3};
        TestResultsHelper.verify("1",10, largestRectangleArea(testCase_1));
        int[] testCase_2 = new int[]{4,2,0,3,2,5};
        TestResultsHelper.verify("2", 6, largestRectangleArea(testCase_2));
        int[] testCase_3 = new int[]{5,6,4,4,4,4};
        TestResultsHelper.verify("3", 24, largestRectangleArea(testCase_3));
    }

    public static void main(String[] args) {
        new FindLargestArea().run();
    }
}
