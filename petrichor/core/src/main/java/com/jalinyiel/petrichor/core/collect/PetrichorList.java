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
    }

    public void leftPush(String... values) {
        Arrays.stream(values).forEach(value::addFirst);
    }

    public void insert(String v, int index) {
        if(index >= value.size())value.add(value.size()-1, v);
        else value.add(index, v);
    }

    public Integer size() {
        return value.size();
    }

    public String index(int i) {
        return value.get(i);
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
        end = end == -1 ? value.size() : end;
        LinkedList<String> newList = new LinkedList(value.stream().skip(start).limit(end - start).collect(Collectors.toList()));
        value = newList;
        return newList;
    }

    public List<String> range(int start, int end) {
        end = end == -1 ? value.size() : end;
        return value.stream().skip(start).limit(end - start).collect(Collectors.toList());
    }

    public String toString() {
        String elements = value.stream().collect(Collectors.joining(","));
        return String.format("[%s]", elements);
    }
}
