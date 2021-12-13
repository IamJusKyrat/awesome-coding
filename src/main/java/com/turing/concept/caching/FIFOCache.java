package com.turing.concept.caching;

import java.util.Objects;

public class FIFOCache extends ArrayCache {
    private int currentIndex;
    @Override
    public final synchronized void addElement(Object key,Object value) {
        int index;
        CacheElement oldEntry = tableOfEntries.get(key);
        if (Objects.nonNull(oldEntry)) {
            oldEntry.setValue(value);
            oldEntry.setKey(key);
            return;
        }

        // If we haven't filled the cache yet, put it at the end.
        if (!isFull()) {
            index = numberOfEntries;
            ++numberOfEntries;
        } else {
            // Otherwise, replace the current pointer, entry with the new one
            index = currentIndex;
            // in order to make circular FIFO
            if (++currentIndex >= numberOfEntries) currentIndex = 0;
            tableOfEntries.remove(cacheMemory[index].getKey());
        }

        cacheMemory[index].setValue(value);
        cacheMemory[index].setKey(key);
        tableOfEntries.put(key, cacheMemory[index]);
    }
}
