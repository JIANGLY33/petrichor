package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.collect.PetrichorValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContextUtil<T> {

    @Autowired
    PetrichorContext petrichorContext;

    public <T extends PetrichorValue> T getValue(String key) {
        PetrichorDict dict = getDataDict();
        Optional<PetrichorObject> petrichorValue = dict.getByKey(key);
        T petrichorList = (T) petrichorValue.get().getPetrichorValue();
        return petrichorList;
    }

    public boolean keyExist(String key) {
        PetrichorDict dict = getDataDict();
        return dict.exist(key);
    }

    public PetrichorObject delete(String key) {
        PetrichorDict dict = getDataDict();
        return dict.delete(key);
    }

    private PetrichorDict getDataDict() {
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        PetrichorDict dict = petrichorDb.getKeyValues();
        return dict;
    }
}
