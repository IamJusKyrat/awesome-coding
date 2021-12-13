package com.turing.concept.caching;

import java.util.Objects;

public class RandomCache extends ArrayCache {
    @Override
    public synchronized void addElement(Object key,Object value) {
        int index;
        CacheElement entry = tableOfEntries.get(key);

        // Update in place and return
        if (Objects.nonNull(entry)) {
            entry.setValue(value);entry.setKey(key);
            return;
        }

        // If we haven't filled the cache yet, put it at the end, else replace at a random place.
        if (!isFull()) {
            index = numberOfEntries;
            ++numberOfEntries;
        } else {
            index = (int) (cacheSize * Math.random());
            cacheMemory[index] = null;
        }

        cacheMemory[index].setValue(value);
        cacheMemory[index].setKey(key);
        tableOfEntries.put(key, cacheMemory[index]);
    }
}
