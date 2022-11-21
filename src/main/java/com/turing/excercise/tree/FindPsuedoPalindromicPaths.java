package com.turing.excercise.tree;


public class FindPsuedoPalindromicPaths {
    public int pseudoPalindromicPaths (TreeNode root) {
        //We can keep a count of all items on that path. [0-9]
        //If zero or one is odd then it is a palindrome, else not
        //Also we only have to calcualate palindrome at the leafs
        if(root == null) return 0;
        int[] REP_INDEX = new int[9];
        return calculatePalindromicPaths(root, REP_INDEX);
    }

    private int calculatePalindromicPaths(TreeNode curr, int[] REP_INDEX) {
        REP_INDEX[curr.val - 1]++;
        int result = 0;
        if(curr.left != null) {
            result += calculatePalindromicPaths(curr.left, REP_INDEX);
        }

        if(curr.right != null) {
            result += calculatePalindromicPaths(curr.right, REP_INDEX);
        }

        if(curr.left == null && curr.right == null) {
            result += verifyPsuedoPalindrome(REP_INDEX) ? 1 : 0;
        }
        REP_INDEX[curr.val - 1]--;
        return result;
    }

    private boolean verifyPsuedoPalindrome(int[] REP_INDEX) {
        int oddCount = 0;
        for(int i = 0; i < REP_INDEX.length; i++) {
            if(REP_INDEX[i]%2 != 0) oddCount++;
        }

        return oddCount <= 1;
    }

    public void run() {
        System.out.println(pseudoPalindromicPaths(createTree()));
    }

    public static void main(String[] args) {
        new FindPsuedoPalindromicPaths().run();
    }

    private TreeNode createTree() {
        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(1);
        TreeNode f = new TreeNode(1);
        TreeNode b = new TreeNode(3, d, e);
        TreeNode c = new TreeNode(1, null, f);
        return new TreeNode(2, b, c);
    }
}
