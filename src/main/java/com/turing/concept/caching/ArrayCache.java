package com.turing.concept.caching;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class ArrayCache implements Cache {

    protected final int DEFAULT_CACHE_SIZE = 100;
    // TODO: Provide constructor for user-input cache size.
    protected final int cacheSize = DEFAULT_CACHE_SIZE;
    protected final CacheElement[] cacheMemory = new CacheElement[cacheSize];
    protected final Map<Object, CacheElement> tableOfEntries = new HashMap<>();
    protected int numberOfEntries = 0;

    //Always removing from the last added entry, no particular cache replacement policy applied
    public synchronized void addElement(Object key, Object value) {
        int index = numberOfEntries - 1;

        final CacheElement entry = tableOfEntries.get(key);
        if(Objects.nonNull(entry)) {
            entry.setValue(value);
            entry.setKey(key);
        }

        cacheMemory[index] = new CacheElement();
    }

    public Object getElement(Object key) {
        //TODO: If the key is not found we need to access the datastore and get the object and add to our current cache store.
        if (key == null) return null;
        return tableOfEntries.get(key).getValue();
    }

    public boolean isFull() {
        return numberOfEntries == cacheSize;
    }

}
