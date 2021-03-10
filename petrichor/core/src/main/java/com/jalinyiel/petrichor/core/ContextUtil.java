package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.collect.PetrichorList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ContextUtil {

    @Autowired
    PetrichorContext petrichorContext;

    public PetrichorList getValue(String key) {
        PetrichorDict dict = getDataDict();
        Optional<PetrichorObject> petrichorValue = dict.getByKey(key);
        if (!petrichorValue.isPresent()) {
            //todo 当键对应当值不存在时
        }
        PetrichorList petrichorList = (PetrichorList) petrichorValue.get().getPetrichorValue();
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
