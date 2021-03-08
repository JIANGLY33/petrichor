package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.collect.PetrichorString;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PetrichorDict  {

    private final ObjectType keyType = ObjectType.PETRICHOR_STRING;

    private final ObjectType valueType = ObjectType.PETRICHOR_LIST;

    private HashMap<PetrichorObject, PetrichorObject> dict;

    public PetrichorDict(HashMap<PetrichorObject, PetrichorObject> dict) {
        this.dict = dict;
    }

    public PetrichorDict() {
        this.dict = new HashMap<>();
    }

    public Optional<PetrichorObject> getByKey(String key) {
        return this.dict.entrySet().stream().filter(entry -> {
           PetrichorString petrichorString = (PetrichorString)entry.getKey().getPetrichorValue();
           return petrichorString.getValue().equals(key);
        }).map(Map.Entry::getValue).findAny();
    }

    public int size() { return dict.size(); }
}
