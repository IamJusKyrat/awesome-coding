package com.turing.excercise.tree;

import java.util.LinkedList;
import java.util.Queue;

class SerializableTreeNode {
    int val;
    SerializableTreeNode left, right;

    public SerializableTreeNode(int val) {this.val = val;}
}

public class SerializeTree {
    String DELIM =  ",";
    String SENTINAL = "N";
    // Encodes a tree to a single string.
    public String serialize(SerializableTreeNode root) {
        if(root == null) return "";
        Queue<SerializableTreeNode> q = new LinkedList<>();
        q.add(root);
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            SerializableTreeNode n = q.poll();
            if(n == null) {
                sb.append(SENTINAL).append(DELIM);
            } else {
                sb.append(n.val).append(DELIM);
                q.add(n.left);
                q.add(n.right);
            }
        }
        return sb.substring(0, sb.length()-1).toString();
    }

    // Decodes your encoded data to tree.
    public SerializableTreeNode deserialize(String data) {
        if(data == null || data.length() == 0) return null;
        String[] e = data.split(DELIM);
        Queue<SerializableTreeNode> q = new LinkedList<>();
        SerializableTreeNode root = new SerializableTreeNode(Integer.parseInt(e[0]));
        q.add(root);
        int i = 1;
        while(i < e.length && !q.isEmpty()) {
            SerializableTreeNode node = q.poll();
            if(e[i].equals(SENTINAL)) {
                node.left = null;
            } else {
                SerializableTreeNode left = new SerializableTreeNode(Integer.parseInt(e[i]));
                node.left = left;
                q.add(left);
            }
            i++;
            if(i == e.length) break;
            if(e[i].equals(SENTINAL)) {
                node.right = null;
            } else {
                SerializableTreeNode right = new SerializableTreeNode(Integer.parseInt(e[i]));
                node.right = right;
                q.add(right);
            }
        }
        return root;
    }
}

class Runner {
    public static void main(String[] args) {
        SerializableTreeNode root = new SerializableTreeNode(1);
        root.left = new SerializableTreeNode(2);
        root.right = new SerializableTreeNode(3);
        root.right.right = new SerializableTreeNode(5);
        SerializeTree st = new SerializeTree();
        System.out.println(st.serialize(root));
    }
}
