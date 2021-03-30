package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.collect.PetrichorString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class ExpireDict {
    private HashMap<PetrichorObject, Long> dict;

    public ExpireDict(HashMap<PetrichorObject, Long> dict) {
        this.dict = dict;
    }

    public ExpireDict() {
        this.dict = new HashMap<>();
    }

    public int size() {
        return dict.size();
    }

    public long put(PetrichorObject petrichorObject, Long time) {
        dict.put(petrichorObject,time);
        return time;
    }

    public Optional<Long> get(String key) {
        return this.dict.entrySet().stream().filter(entry-> {
            PetrichorString k = (PetrichorString)entry.getKey().getPetrichorValue();
            return k.get().equals(key);
        }).findAny().map(Map.Entry::getValue);
    }

    public long remove(String key) {
        Iterator<Map.Entry<PetrichorObject, Long>> iterator = dict.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<PetrichorObject, Long> cur = iterator.next();
            PetrichorString curKey = (PetrichorString)cur.getKey().getPetrichorValue();
            if (key.equals(curKey.getValue())) {
                iterator.remove();
                return cur.getValue();
            }
        }
        return -1L;
    }
}
