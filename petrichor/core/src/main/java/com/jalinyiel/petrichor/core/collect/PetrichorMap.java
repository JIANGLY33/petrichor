package com.jalinyiel.petrichor.core.collect;

import java.util.*;
import java.util.stream.Collectors;

public class PetrichorMap implements PetrichorValue{

    private static final long serialVersionUID = -3011382874601768229L;

    private final Map<String,String> value;

    public PetrichorMap() {
        this.value = new HashMap<>();
    }

    public PetrichorMap(Map<String, String> value) {
        this.value = value;
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(value.get(key));
    }

    public Optional<String> delete(String key) {
        return Optional.ofNullable(value.remove(key));
    }

    public Integer size() {
        return value.size();
    }

    public Set<String> keys() {
        return value.keySet();
    }

    public List<String> values() {
        return value.values().stream().collect(Collectors.toList());
    }

    public String toString() {
        String elements = value.entrySet().stream()
                .map(entrySet -> String.format("%s->%s",entrySet.getKey(),entrySet.getValue()))
                .collect(Collectors.joining(","));
        return String.format("{%s}",elements);
    }
}
