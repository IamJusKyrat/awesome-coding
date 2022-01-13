package com.turing.concept.caching;

public interface Cache {
    void addElement(Object key, Object value);
    Object getElement(Object key);
}
