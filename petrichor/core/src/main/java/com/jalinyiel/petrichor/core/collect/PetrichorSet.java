package com.jalinyiel.petrichor.core.collect;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PetrichorSet implements PetrichorValue{

    private static final long serialVersionUID = -8367086252504340332L;

    private final Set<String> value;

    public PetrichorSet() {
        this.value = new HashSet<>();
    }

    public PetrichorSet(Set<String> value) {
        this.value = value;
    }

    public void add(String... elements) {
        Arrays.stream(elements).forEach(value::add);
    }

    public void remove(String... elements) {
        Arrays.stream(elements).forEach(value::remove);
    }

    public Integer size() {
        return value.size();
    }

    public Set<String> getValue() {
        return value;
    }

    public PetrichorSet union(PetrichorSet petrichorSet) {
        value.addAll(petrichorSet.getValue());
        return this;
    }

    public PetrichorSet intersect(PetrichorSet petrichorSet) {
        value.retainAll(petrichorSet.getValue());
        return this;
    }

    public PetrichorSet complementary(PetrichorSet petrichorSet) {
        value.removeAll(petrichorSet.getValue());
        return this;
    }

    public String toString() {
        String elements = value.stream().collect(Collectors.joining(","));
        return String.format("(%s)",elements);
    }
}
