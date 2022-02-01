package com.turing.excercise.graph;

import com.turing.excercise.TestResultsHelper;

import java.util.*;

/**
 * Category: Medium
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take
 * course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers,
 * return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * Reference: https://leetcode.com/problems/course-schedule-ii/submissions/
 */
public class CourseSchedule {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //Lets do topological sort on the courses

        //Create an adjacency list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int[] prereq : prerequisites) {
            int to = prereq[0]; int from = prereq[1];
            List<Integer> list = adj.getOrDefault(from, new ArrayList<>());
            list.add(to);
            adj.put(from, list);
        }

        //Find Cycle in order of courses using tarjans
        boolean[] visited = new boolean[numCourses];
        boolean[] stack = new boolean[numCourses];
        for(int from = 0; from < numCourses; from++) {
            if(findCycle(from, adj, visited, stack))
                return new int[0];
        }

        //Create topological sort
        visited = new boolean[numCourses];
        Stack<Integer> order = new Stack<>();

        for(int from = 0; from < numCourses; from++) {
            dfs(from, adj, visited, order);
        }

        return returnArray(order);
    }

    //Tarjan's Algorithm O(V+E)
    private boolean findCycle(Integer from, Map<Integer, List<Integer>> adj, boolean[] visited, boolean[] stack) {
        visited[from] = true;
        stack[from] = true;

        //recursive call to all the adjacent vertices
        if(adj.containsKey(from)) {
            for (int to: adj.get(from)) {
                //if not already visited
                if (!visited[to] && findCycle(to, adj, visited, stack)) return true;
                else if (stack[to]) return true;
            }
        }

        //if reached here means cycle has not found in DFS from this vertex reset
        stack[from] = false;
        return false;
    }

    private void dfs(int from, Map<Integer, List<Integer>> adj, boolean[] visited, Stack<Integer> order) {
        if(visited[from]) return;
        if(adj.containsKey(from)) {
            for(int to: adj.get(from)) {
                dfs(to, adj, visited, order);
            }
        }
        visited[from] = true;
        order.push(from);
    }

    private int[] returnArray(Stack<Integer> stack) {
        int[] courses = new int[stack.size()];
        int i = 0;
        while(!stack.isEmpty()) {
            courses[i++] = stack.pop();
        }

        return courses;
    }

    private void run() {
        int[][] ts1_courseDep =  new int[][]{{1,0}};
        TestResultsHelper.verify("1", new int[]{0,1}, findOrder(2, ts1_courseDep));
        int[][] ts2_courseDep = new int[][] {{1,0},{2,0},{3,1},{3,2}};
        TestResultsHelper.verify("2", new int[]{0,2,1,3}, findOrder(4, ts2_courseDep));
        int[][] ts3_courseDep = new int[][] {{1,0},{0,1}};
        TestResultsHelper.verify("3", new int[0], findOrder(2, ts3_courseDep));
    }

    public static void main(String[] args) {
        new CourseSchedule().run();
    }
}
