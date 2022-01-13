package com.turing.concept.caching;

import java.util.Objects;

public class LRUCache extends QueueCache {
    @Override
    public final synchronized void addElement(Object key, Object value) {
        CacheElement oldEntry = tableOfEntries.get(key);
        if(Objects.nonNull(oldEntry)) {
            oldEntry.setValue(value);oldEntry.setValue(key);
            cacheMemory.remove(oldEntry);
            cacheMemory.add(oldEntry);
            return;
        }

        // If we haven't filled the cache yet, place in next available spot and move to front.
        if(!isFull()) {
            ++numberOfEntries;
        } else {
            // We replace the tail of the list.
            CacheElement removed = cacheMemory.pop();
            tableOfEntries.remove(removed.getKey());
        }

        CacheElement newEntry = new CacheElement();
        newEntry.setKey(key);newEntry.setValue(value);
        cacheMemory.add(newEntry);
        tableOfEntries.put(key, newEntry);
    }
}
