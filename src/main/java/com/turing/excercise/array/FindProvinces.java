package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

import java.util.HashSet;
import java.util.Set;

class Union{
    int[] parent;
    int[] sz;
    Union(int size) {
        parent = new int[size];
        sz = new int[size];
        for(int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    int find(int i) {
        int root = i;
        while(root != parent[root]) {root = parent[root];}

        while(i!=root) {
            int next = parent[i];
            parent[i] = root;
            i = next;
        }
        return root;
    }

    void union(int x, int y) {
        int root1 = find(x);int root2 = find(y);

        if(sz[root1] > sz[root2]) {
            parent[root2] = root1;
            sz[root1] = sz[root1] + sz[root2];
        } else {
            parent[root1] = root2;
            sz[root2] = sz[root2] + sz[root1];
        }
    }
}

public class FindProvinces {
    public int findCircleNum(int[][] isConnected) {
        int size = isConnected.length;
        if(size == 1) return 1;

        Union u = new Union(size);
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(i!=j && isConnected[i][j] == 1) {
                    u.union(i, j);
                }
            }
        }
        Set<Integer> provinces = new HashSet<>();
        for(int i = 0; i < size; i++) {
            if(provinces.contains(u.parent[i])) continue;
            provinces.add(u.parent[i]);
        }

        return provinces.size();
    }

    public void run() {
        int[][] testcase_1 = new int[][] {
                {1,0,0,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,1,1}
        };
        TestResultsHelper.verify("1", 1, findCircleNum(testcase_1));
    }

    public static void main(String[] args) {
        new FindProvinces().run();
    }
}




