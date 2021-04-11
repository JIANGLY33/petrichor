package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.collect.PetrichorString;

import java.time.Instant;
import java.util.*;

public class PetrichorDict {

    private final ObjectType keyType = ObjectType.PETRICHOR_STRING;

    private final ObjectType valueType = ObjectType.PETRICHOR_LIST;

    private HashMap<PetrichorObject, PetrichorObject> dict;

    public PetrichorDict(HashMap<PetrichorObject, PetrichorObject> dict) {
        this.dict = dict;
    }

    public PetrichorDict() {
        this.dict = new HashMap<>();
    }

    public Optional<PetrichorObject> getKey(String key) {
        return this.dict.entrySet().stream().filter(entry -> {
            PetrichorString petrichorString = (PetrichorString) entry.getKey().getPetrichorValue();
            return petrichorString.getValue().equals(key);
        }).map(Map.Entry::getKey).findAny();
    }

    public Optional<PetrichorObject> getByKey(String key) {
        return this.dict.entrySet().stream().filter(entry -> {
            PetrichorString petrichorString = (PetrichorString) entry.getKey().getPetrichorValue();
            return petrichorString.getValue().equals(key);
        }).map(Map.Entry::getValue).findAny();
    }

    public boolean exist(String key) {
        return this.dict.entrySet().stream().anyMatch(entry -> {
            PetrichorString petrichorString = (PetrichorString) entry.getKey().getPetrichorValue();
            return petrichorString.getValue().equals(key);
        });
    }

    public PetrichorObject put(PetrichorObject key, PetrichorObject value) {
        return dict.put(key, value);
    }

    public int size() {
        return dict.size();
    }

    public PetrichorObject delete(String key) {
        Iterator<Map.Entry<PetrichorObject, PetrichorObject>> iterator = dict.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<PetrichorObject, PetrichorObject> cur = iterator.next();
            PetrichorString curKey = (PetrichorString)cur.getKey().getPetrichorValue();
            if (key.equals(curKey.getValue())) {
                iterator.remove();
                return cur.getValue();
            }
        }
        // 目前的做法是先检查key是否存在再delete，理论上永远不会走到这里
        return null;
    }

    public int keyTaskIncre(String key) {
        return this.dict.entrySet().stream().filter(entry -> {
            PetrichorString petrichorString = (PetrichorString) entry.getKey().getPetrichorValue();
            return petrichorString.getValue().equals(key);
        }).map(entry -> entry.getKey().countIncre()).findAny().get();
    }

    public Map<PetrichorObject,PetrichorObject> getDict() {
        return dict;
    }

}
