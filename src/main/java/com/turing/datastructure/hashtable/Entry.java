package com.turing.datastructure.hashtable;

import java.util.Iterator;

/**
 * TODO: Add Documentation
 * @param <K>
 * @param <V>
 */
class Entry<K,V>{
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
