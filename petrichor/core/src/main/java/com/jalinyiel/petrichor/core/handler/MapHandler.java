package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.collect.PetrichorMap;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.ops.MapOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@DataType(type = TaskType.MAP_TASK)
public class MapHandler extends PetrichorHandler implements MapOps {

    @Autowired
    PetrichorContext petrichorContext;

    @Autowired
    ContextUtil<PetrichorMap> contextUtil;

    private final ObjectType VALUE_TYPE = ObjectType.PETRICHOR_MAP;

    private final ObjectEncoding VALUE_ENCODING = ObjectEncoding.HASH_MAP;

    @Override
    public ResponseResult<Void> mapSet(String key, String entryKey, String entryValue) {
        if (!contextUtil.keyExist(key)) {
            PetrichorMap petrichorMap = new PetrichorMap();
            petrichorMap.put(entryKey, entryValue);
            PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
            PetrichorDict dict = petrichorDb.getKeyValues();
            dict.put(PetrichorObjectFactory.of(PetrichorUtil.KEY_TYPE, PetrichorUtil.KEY_ENCODING, new PetrichorString(key)),
                    PetrichorObjectFactory.of(VALUE_TYPE, VALUE_ENCODING, petrichorMap));
        } else {
            PetrichorMap petrichorMap = contextUtil.getValue(key);
            petrichorMap.put(entryKey, entryValue);
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    public ResponseResult<String> mapGet(String key, String filed) {
        PetrichorMap petrichorMap = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorMap.get(filed).orElse(""));
    }

    @Override
    public ResponseResult<String> mapDelete(String key, String field) {
        PetrichorMap petrichorMap = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorMap.delete(field).orElse(""));
    }

    @Override
    public ResponseResult<Integer> mapSize(String key) {
        PetrichorMap petrichorMap = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorMap.size());
    }

    @Override
    public ResponseResult<String> mapKeys(String key) {
        PetrichorMap petrichorMap = contextUtil.getValue(key);
        String keys = petrichorMap.keys().stream().collect(Collectors.joining(","));
        return ResponseResult.successResult(CommonResultCode.SUCCESS, String.format("(%s)", keys));
    }

    @Override
    public ResponseResult<String> mapValues(String key) {
        PetrichorMap petrichorMap = contextUtil.getValue(key);
        String values = petrichorMap.values().stream().collect(Collectors.joining(","));
        return ResponseResult.successResult(CommonResultCode.SUCCESS, String.format("[%s]", values));
    }
}
