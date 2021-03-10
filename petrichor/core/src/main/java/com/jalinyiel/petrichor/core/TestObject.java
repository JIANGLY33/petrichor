package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.handler.DataType;
import com.jalinyiel.petrichor.core.task.TaskType;

import java.util.Arrays;
import java.util.stream.Collectors;

@DataType(type = TaskType.LIST_TASK)
public class TestObject {
    public void testPrint(String... args) {
        System.out.println(Arrays.stream(args).collect(Collectors.joining()));
    }
}
