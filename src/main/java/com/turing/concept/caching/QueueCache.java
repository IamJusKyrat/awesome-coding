package com.turing.concept.caching;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class QueueCache implements Cache{
    protected final int DEFAULT_CACHE_SIZE = 100;
    //TODO: Add a constructor which allows for cacheSize
    protected int cacheSize = DEFAULT_CACHE_SIZE;
    protected final Deque<CacheElement> cacheMemory = new ArrayDeque<>();
    protected final HashMap<Object, CacheElement> tableOfEntries = new HashMap<>();
    protected int numberOfEntries = 0;

    @Override
    public void addElement(Object key, Object value) {
        //Placeholder
    }

    @Override
    public Object getElement(Object key) {
        if(key == null) return null;
        return tableOfEntries.get(key);
    }

    protected boolean isFull() {
        return numberOfEntries == cacheSize;
    }
}
