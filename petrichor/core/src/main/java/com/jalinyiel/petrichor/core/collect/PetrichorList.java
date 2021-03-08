package com.jalinyiel.petrichor.core.collect;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PetrichorList implements PetrichorValue {

    private static final long serialVersionUID = 1647799370253517845L;

    private LinkedList<String> value;

    public PetrichorList() {
        this.value = new LinkedList<>();
    }

    public PetrichorList(LinkedList<String> value) {
        this.value = value;
    }

    public void rightPush(String[] values) {
        Arrays.stream(values).forEach(value::addLast);
        System.out.println(value.size());
    }

    public void leftPush(String... values) {
        Arrays.stream(values).forEach(value::addFirst);
    }

    public void insert(String v, int index) {
        value.add(index, v);
    }

    public Integer size() {
        System.out.println(value.size());
        return value.size();
    }

    public String leftPop() {
        return value.removeFirst();
    }

    public String rightPop() {
        return value.removeLast();
    }

    public String set(String v, int index) {
        return value.set(index, v);
    }

    public List<String> trim(int start, int end) {
        LinkedList<String> newList = (LinkedList<String>) value.stream().skip(start).limit(end - start).collect(Collectors.toList());
        value = newList;
        return newList;
    }

    public List<String> range(int start, int end) {
        return value.stream().skip(start).limit(end - start).collect(Collectors.toList());
    }
}
