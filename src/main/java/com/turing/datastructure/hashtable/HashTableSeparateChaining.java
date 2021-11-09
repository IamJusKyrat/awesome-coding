package com.turing.datastructure.hashtable;

import com.turing.datastructure.linkedlist.DoublyLinkedList;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

/**
 * TODO: Add Documentation
 * @param <K>
 * @param <V>
 */
class Entry<K,V> {
    int hash;
    K key; V value;
    public Entry(final K key, final V value){
        this.key= key;
        this.value = value;
        this.hash = key.hashCode();
    }

    public boolean equals(Entry<K, V> other) {
        if(hash != other.hash) return false;
        return key.equals(other.key);
    }

    @Override public String toString(){
        return key + " => " + value;
    }
}

/**
 * TODO: Add documentation
 * @param <K>
 * @param <V>
 */
public class HashTableSeparateChaining<K,V> implements Iterable<K> {
    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private DoublyLinkedList<Entry<K,V>>[] table;

    public HashTableSeparateChaining() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity){
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity, double maxLoadFactor) {
        if(capacity < 0) throw new IllegalArgumentException("Illegal capacity");
        if(maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
            throw new IllegalArgumentException("Illegal max load factor");
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        this.threshold = (int) (this.capacity* maxLoadFactor);
        this.table = new DoublyLinkedList[this.capacity];
    }

    public int size() { return size; }
    public boolean isEmpty() {return size == 0; }

    private int normalizeIndex(int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean containsKey(K key) { return hasKey(key); }
    public boolean hasKey(K key) {
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    public V put(K key, V value) {return insert(key, value); }
    public V add(K key, V value) {return insert(key, value); }

    private V insert(K key, V value) {
        if(key == null) throw new IllegalArgumentException("Key is null");
        Entry<K,V> entry = new Entry<>(key, value);
        int bucketIndex = normalizeIndex(entry.hash);
        return bucketInsertEntry(bucketIndex, entry);
    }

    public V get(K key) {
        if(key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K,V> entry = bucketSeekEntry(bucketIndex, key);
        if(entry != null) return entry.value;
        return null;
    }

    public V remove(K key) {
        if(key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    private V bucketRemoveEntry(int bucketIndex, K key) {
        final Entry<K,V> entry = bucketSeekEntry(bucketIndex, key);
        if(entry != null) {
            DoublyLinkedList<Entry<K,V>> bucket = table[bucketIndex];
            bucket.remove(entry); --size;
            return entry.value;
        }
        return null;
    }

    private Entry<K,V> bucketSeekEntry(int bucketIndex, K key) {
        if(key == null) return null;
        DoublyLinkedList<Entry<K,V>> bucket = table[bucketIndex];
        if(bucket == null) return null;
        for (Entry<K,V> entry: bucket){
            if(entry.key.equals(key)) return entry;
        }
        return null;
    }

    private V bucketInsertEntry(int bucketIndex, Entry<K,V> entry) {
        DoublyLinkedList<Entry<K,V>> bucket = table[bucketIndex];
        if(bucket == null) bucket = new DoublyLinkedList<>();
        Entry<K,V> existentEntry = bucketSeekEntry(bucketIndex, entry.key);
        if(existentEntry == null){
            bucket.insert(entry);
            if(++size > threshold) resizeTable();
            return null;
        } else {
            V oldVal = existentEntry.value;
            existentEntry.value = entry.value;
            return oldVal;
        }
    }

    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);
        DoublyLinkedList<Entry<K,V>>[] newtable = new DoublyLinkedList[capacity];
        for(int i = 0; i < table.length; i++) {
            if(table[i] != null) {
                for (Entry<K,V> entry : table[i]) {
                    int bucketIndex = normalizeIndex(entry.hash);
                    DoublyLinkedList<Entry<K,V>> bucket = newtable[bucketIndex];
                    if(bucket == null) bucket = new DoublyLinkedList<>();
                    bucket.insert(entry);
                }
                table[i].clear();
                table[i] = null;
            }
        }

        table = newtable;
    }

    public List<K> keys() {return null;}
    public List<V> values() {return null;}

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
