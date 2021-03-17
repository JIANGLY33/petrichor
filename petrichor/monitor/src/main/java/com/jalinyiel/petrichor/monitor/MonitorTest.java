package com.jalinyiel.petrichor.monitor;

import org.apache.lucene.util.RamUsageEstimator;

public class MonitorTest {
    public static void main(String[] args) {
        TestObject testObject = new TestObject();
        System.out.println(String.format("first:%d",RamUsageEstimator.sizeOf(testObject)));
        testObject.put("hello","world");
        System.out.println(String.format("second:%d",RamUsageEstimator.sizeOf(testObject)));
        testObject.put("jalinyiel","world");
        System.out.println(String.format("second:%d",RamUsageEstimator.sizeOf(testObject)));
    }
}
