package com.turing.datastructure;

import com.turing.datastructure.array.DynamicArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {
    // TODO: Add tests to check cases for your dynamic array
    @Test
    void AddElementsTest() {
        final DynamicArray<Integer> values = new DynamicArray<>();
        for(int i = 1; i<=16; i++) {
            values.add(i);
        }
        assertEquals(16, values.size());
        assertEquals(16, (int) values.get(15));

        values.add(17);
        assertEquals(values.size(), 17);

        final DynamicArray<Integer> addValues = new DynamicArray<>();
        for(int i = 18; i<=100; i++) {
            addValues.add(i);
        }
        values.addAll(addValues);
        assertEquals(100, values.size());
        assertEquals(100, (int) values.get(99));
    }

    @Test
    void RemoveElementsTest() {
        final DynamicArray<Integer> values = new DynamicArray<>();
        for(int i = 1; i<=16; i++) {
            values.add(i);
        }
        assertEquals(16, values.size());
        assertEquals(16, (int) values.get(15));

        for(int i = 10; i>=0; i--) {
            values.removeAt(i);
        }
        assertEquals(5, values.size());
        assertEquals(11, (int) values.get(0));
        assertEquals(15, (int) values.get(4));


    }

}
