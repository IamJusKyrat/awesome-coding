package com.turing.concept.caching;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CacheElement {
    private Object key;
    private Object value;

    private int index;
    private int hitCount;
}
