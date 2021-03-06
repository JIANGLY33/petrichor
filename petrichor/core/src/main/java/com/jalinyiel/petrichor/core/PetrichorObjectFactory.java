package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.collect.PetrichorValue;

public class PetrichorObjectFactory {
    public static PetrichorObject of(ObjectType type, ObjectEncoding encoding, PetrichorValue petrichorValue) {
        return new PetrichorObject(type, encoding, petrichorValue);
    }
}
