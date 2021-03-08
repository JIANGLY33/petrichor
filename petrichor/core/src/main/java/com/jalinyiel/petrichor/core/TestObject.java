package com.jalinyiel.petrichor.core;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestObject {
    public void testPrint(String... args) {
        System.out.println(Arrays.stream(args).collect(Collectors.joining()));
    }
}
