package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.collect.PetrichorValue;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
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
}
