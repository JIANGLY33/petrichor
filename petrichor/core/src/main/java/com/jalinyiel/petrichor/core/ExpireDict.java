package com.jalinyiel.petrichor.core;

import java.util.HashMap;

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
}
