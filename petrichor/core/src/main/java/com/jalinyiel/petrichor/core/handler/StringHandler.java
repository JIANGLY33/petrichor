package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.collect.PetrichorList;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.collect.PetrichorValue;
import com.jalinyiel.petrichor.core.ops.StringOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@DataType(type = TaskType.STRING_TASK)
public class StringHandler extends PetrichorHandler implements StringOps {

    @Autowired
    PetrichorContext petrichorContext;

    @Autowired
    ContextUtil<PetrichorString> contextUtil;

    private final ObjectType VALUE_TYPE = ObjectType.PETRICHOR_STRING;

    private final ObjectEncoding VALUE_ENCODING = ObjectEncoding.RAW_STRING;

    @Override
    public ResponseResult<Void> set(String key, String value) {
        if (!contextUtil.keyExist(key)) {
            PetrichorString petrichorString = new PetrichorString(value);
            PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
            PetrichorDict dict = petrichorDb.getKeyValues();
            dict.put(PetrichorObjectFactory.of(PetrichorUtil.KEY_TYPE, PetrichorUtil.KEY_ENCODING, new PetrichorString(key)),
                    PetrichorObjectFactory.of(VALUE_TYPE, VALUE_ENCODING, petrichorString));
        }else {
            PetrichorString petrichorString = contextUtil.getValue(key);
            petrichorString.set(key);
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    public ResponseResult<String> get(String key) {
        PetrichorString petrichorString = contextUtil.getValue(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorString.get());
    }

    @Override
    public ResponseResult<Void> setWithSecondsExpire(String key, long seconds, String value) {
        return null;
    }

    @Override
    public ResponseResult<Void> setWithMileSecondsExpire(String key, long mileSeconds, String value) {
        return null;
    }

    @Override
    public ResponseResult<String> multipleGet(String... keys) {
        String petrichorStrings = Arrays.stream(keys)
                .map(key -> (PetrichorString)contextUtil.getValue(key))
                .map(petrichorString -> petrichorString.get())
                .collect(Collectors.joining("\n"));
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorStrings);
    }
}
