package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.collect.PetrichorValue;
import lombok.EqualsAndHashCode;

import java.util.Objects;

public class PetrichorObject {

    private final ObjectType type;

    private final ObjectEncoding encoding;

    private final PetrichorValue petrichorValue;

    private int lru;

    private int count;

    public PetrichorObject(ObjectType type, ObjectEncoding encoding, PetrichorValue petrichorValue) {
        this.type = type;
        this.encoding = encoding;
        this.petrichorValue = petrichorValue;
        this.count = 0;
        this.lru = 0;
    }

    public ObjectType getType() {
        return type;
    }

    public ObjectEncoding getEncoding() {
        return encoding;
    }

    public PetrichorValue getPetrichorValue() {
        return petrichorValue;
    }

    public int countIncre() {
        count += 1;
        return this.count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetrichorObject that = (PetrichorObject) o;
        return type == that.type && encoding == that.encoding && petrichorValue.equals(that.petrichorValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, encoding, petrichorValue);
    }
}
