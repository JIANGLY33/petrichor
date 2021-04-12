package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.check.CheckKey;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.ops.StringOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import com.jalinyiel.petrichor.core.util.PetrichorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
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
        } else {
            Optional<PetrichorString> optionalPetrichorString = this.getValue(key);
            //键存在，但是类型不同
            if (optionalPetrichorString.isPresent()) {
                return ResponseResult.failedResult(CommonResultCode.TYPE_ERROR, "key already exist!");
            }
            optionalPetrichorString.get().set(key);
        }
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    @CheckKey
    public ResponseResult<String> get(String key) {
        Optional<PetrichorString> optionalPetrichorString = getValue(key);
        return optionalPetrichorString.isPresent() ?
                ResponseResult.successResult(CommonResultCode.SUCCESS, optionalPetrichorString.get().getValue()) :
                ResponseResult.failedResult(CommonResultCode.EXCEPTION, "key not exist!");
    }

    @Override
    public ResponseResult<Void> setWithSecondsExpire(String key, long seconds, String value) {
        this.set(key, value);
        contextUtil.setExpire(PetrichorObjectFactory.of(VALUE_TYPE, VALUE_ENCODING, new PetrichorString(key)),
                Instant.now().plusSeconds(seconds).getEpochSecond());
        return ResponseResult.successResult(CommonResultCode.SUCCESS);
    }

    @Override
    public ResponseResult<Void> setWithMileSecondsExpire(String key, long mileSeconds, String value) {
        return null;
    }

    @Override
    public ResponseResult<String> multipleGet(String... keys) {
        String petrichorStrings = Arrays.stream(keys)
                .map(key -> this.getValue(key).get())
                .map(petrichorString -> petrichorString.get())
                .collect(Collectors.joining("\n"));
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorStrings);
    }

    private Optional<PetrichorString> getValue(String key) {
        try {
            PetrichorString petrichorString = contextUtil.getValue(key);
            return Optional.of(petrichorString);
        } catch (ClassCastException classCastException) {
            return Optional.empty();
        }
    }
}
