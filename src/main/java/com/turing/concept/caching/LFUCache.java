package com.turing.concept.caching;

public class LFUCache extends ArrayCache {
    @Override
    public synchronized Object getElement(Object key) {
        CacheElement element = tableOfEntries.get(key);
        if (element != null) {
            element.setHitCount(element.getHitCount() + 1);
            return element;
        }
        return null;
    }

    @Override
    public final synchronized void addElement(Object key, Object value) {
        int index;
        CacheElement oldEntry = tableOfEntries.get(key);
        if (oldEntry != null) {
            oldEntry.setValue(value);
            oldEntry.setKey(key);
            return;
        }

        if (!isFull()) {
            index = numberOfEntries;
            ++numberOfEntries;
        } else {
            CacheElement element = removeLfuElement();
            index = element.getIndex();
            tableOfEntries.remove(element.getKey());

        }

        cacheMemory[index].setValue(value);
        cacheMemory[index].setKey(key);
        cacheMemory[index].setIndex(index);
        tableOfEntries.put(key, cacheMemory[index]);
    }

    public CacheElement removeLfuElement() {
        return leastHit(cacheMemory);
    }

    public static CacheElement leastHit(CacheElement[] elements) {
        CacheElement lowestElement = null;
        for (CacheElement element : elements) {
            if (lowestElement == null) {
                lowestElement = element;
            } else {
                if (element.getHitCount() < lowestElement.getHitCount()) {
                    lowestElement = element;
                }
            }
        }
        return lowestElement;
    }
}
