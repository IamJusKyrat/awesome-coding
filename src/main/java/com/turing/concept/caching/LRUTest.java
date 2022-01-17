package com.turing.concept.caching;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class TestLRUCache {
    class Node {
        int value;
        int index = -1;
        int key;

        public Node() {
        }
    }

    Map<Integer, Node> map;
    Queue<Node> cache;
    int count;
    int capacity;

    public TestLRUCache(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Only positive values accepted.");
        this.cache = new LinkedList<>();
        this.map = new HashMap<>(capacity);
        this.count = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        //If key not in mapping return null;
        //If key is in mapping, push value to end of queue
        //update
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        cache.remove(node);
        cache.offer(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.containsKey(key) ? map.get(key) : new Node();

        if (count >= capacity && node.index == -1) {
            Node removed = cache.poll();
            map.remove(removed.key);
            count--;
        }
        if (node.index != -1) cache.remove(node);
        node.value = value;
        node.index = count;
        node.key = key;
        cache.offer(node);
        map.put(key, node);
        count++;
    }
}

public class LRUTest {
    public static void main(String[] args) {
        TestLRUCache lru = new TestLRUCache(2);
        lru.put(1,1); lru.put(2,2);lru.get(1); printState(lru);
        lru.put(3,3); lru.get(2); printState(lru);
        lru.put(4,4); lru.get(3); printState(lru);
    }

    static void printState(TestLRUCache lru) {
        System.out.println(lru.capacity + " : " + lru.count);
        lru.cache.forEach(element -> System.out.print(element.value + ","));
        System.out.println();
    }
}
