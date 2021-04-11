package com.jalinyiel.petrichor.core.collect;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PetrichorString implements PetrichorValue{

    private static final long serialVersionUID = -3400552608733185473L;

    private String value;

    public PetrichorString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return value;
    }

    public void set(String value) {
        this.value = value;
    }

    public String get() {
        return this.value;
    }
}
