package com.jalinyiel.petrichor.core;

import java.util.HashMap;

public class PetrichorDb {

    private int id;

    private PetrichorDict keyValues;

    private ExpireDict expireKeys;


    public PetrichorDb(int id, PetrichorDict keyValues, ExpireDict expireKeys) {
        this.id = id;
        this.keyValues = keyValues;
        this.expireKeys = expireKeys;
    }

    public int getId() {
        return id;
    }

    public PetrichorDict getKeyValues() {
        return keyValues;
    }

    public ExpireDict getExpireKeys() {
        return expireKeys;
    }
}
