package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.collect.PetrichorList;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.ops.ListOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class ListHandler implements ListOps {

    @Autowired
    PetrichorContext petrichorContext;

    private final ObjectType keyType = ObjectType.PETRICHOR_STRING;

    private final ObjectType valueType = ObjectType.PETRICHOR_LIST;

    private final ObjectEncoding keyEncoding = ObjectEncoding.RAW_STRING;

    private final ObjectEncoding valueEncoding = ObjectEncoding.LINKED_LIST;

    @Override
    public ResponseResult<Void> rightPush(String key, String... values) {
        PetrichorList petrichorList = getValue(key);
        petrichorList.rightPush(values);
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    public ResponseResult<Void> leftPush(String key, String... values) {
        return null;
    }

    @Override
    public ResponseResult<Void> listInsert(String key, boolean isBefore, int pivot, String value) {
        return null;
    }

    @Override
    public ResponseResult<String> listIndex(String key, int index) {
        return null;
    }

    @Override
    public ResponseResult<List<String>> listRange(String key, int start, int end) {
        return null;
    }

    @Override
    public ResponseResult<Integer> listLength(String key) {
        PetrichorList petrichorList = getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorList.size());
    }

    @Override
    public ResponseResult<String> leftPop(String key) {
        return null;
    }

    @Override
    public ResponseResult<String> rightPop(String key) {
        return null;
    }

    @Override
    public ResponseResult<String> listSetByIndex(String key, int index, String value) {
        return null;
    }

    @Override
    public ResponseResult<Integer> listTrim(String key, int start, int end) {
        return null;
    }

    private PetrichorList getValue(String key) {
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        PetrichorDict dict = petrichorDb.getKeyValues();
        Optional<PetrichorObject> petrichorValue = dict.getByKey(key);
        if (!petrichorValue.isPresent()) {
            //todo 当键对应当值不存在时
        }
        PetrichorList petrichorList = (PetrichorList) petrichorValue.get().getPetrichorValue();
        return petrichorList;
    }
}
