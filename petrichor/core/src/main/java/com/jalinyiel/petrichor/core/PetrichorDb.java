package com.jalinyiel.petrichor.core;

import java.util.concurrent.atomic.AtomicLong;

public class PetrichorDb {

    private int id;

    private PetrichorDict keyValues;

    private ExpireDict expireKeys;

    private AtomicLong taskCount;


    public PetrichorDb(int id, PetrichorDict keyValues, ExpireDict expireKeys) {
        this.id = id;
        this.keyValues = keyValues;
        this.expireKeys = expireKeys;
        this.taskCount = new AtomicLong(0);
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

    public AtomicLong getTaskCount() {
        return taskCount;
    }

    public long taskCountIncre(){
        return taskCount.incrementAndGet();
    }
}
