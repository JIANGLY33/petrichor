package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.check.CheckKey;
import com.jalinyiel.petrichor.core.collect.PetrichorMap;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.ops.MapOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import com.jalinyiel.petrichor.core.util.PetrichorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
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
            Optional<PetrichorMap> optionalMap = this.getValue(key);
            if (!optionalMap.isPresent()) {
                return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key type isn't map!");
            }
            optionalMap.get().put(entryKey, entryValue);
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    @CheckKey
    public ResponseResult<String> mapGet(String key, String filed) {
        Optional<PetrichorMap> optionalMap = this.getValue(key);
        if (!optionalMap.isPresent()) {
            return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key type isn't map!");
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS, optionalMap.get().get(filed).orElse(""));
    }

    @Override
    @CheckKey
    public ResponseResult<String> mapDelete(String key, String field) {
        Optional<PetrichorMap> optionalMap = this.getValue(key);
        if (!optionalMap.isPresent()) {
            return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key type isn't map!");
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS, optionalMap.get().delete(field).orElse(""));
    }

    @Override
    @CheckKey
    public ResponseResult<Integer> mapSize(String key) {
        Optional<PetrichorMap> optionalMap = this.getValue(key);
        if (!optionalMap.isPresent()) {
            return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key type isn't map!");
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS, optionalMap.get().size());
    }

    @Override
    @CheckKey
    public ResponseResult<String> mapKeys(String key) {
        Optional<PetrichorMap> optionalMap = this.getValue(key);
        if (!optionalMap.isPresent()) {
            return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key type isn't map!");
        }
        String keys = optionalMap.get().keys().stream().collect(Collectors.joining(","));
        return ResponseResult.successResult(CommonResultCode.SUCCESS, String.format("(%s)", keys));
    }

    @Override
    @CheckKey
    public ResponseResult<String> mapValues(String key) {
        Optional<PetrichorMap> optionalMap = this.getValue(key);
        if (!optionalMap.isPresent()) {
            return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key type isn't map!");
        }
        String values = optionalMap.get().values().stream().collect(Collectors.joining(","));
        return ResponseResult.successResult(CommonResultCode.SUCCESS, String.format("[%s]", values));
    }

    private Optional<PetrichorMap> getValue(String key) {
        try {
            PetrichorMap petrichorMap = contextUtil.getValue(key);
            return Optional.of(petrichorMap);
        } catch (ClassCastException classCastException) {
            return Optional.empty();
        }
    }
}
