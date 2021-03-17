package com.jalinyiel.petrichor.monitor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestObject {
    private List<Integer> list;

    private Map<String, String> map;

    public TestObject() {
        list = Arrays.asList(1,2,3,4,5,6);
        map = new HashMap<>();
    }

    public void put(String key, String value) {
        map.put(key, value);
    }
}
