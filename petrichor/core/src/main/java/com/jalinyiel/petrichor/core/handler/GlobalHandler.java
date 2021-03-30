package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.check.CheckKey;
import com.jalinyiel.petrichor.core.ops.GlobalOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Component
@DataType(type = TaskType.GLOBAL_TASK)
public class GlobalHandler extends PetrichorHandler implements GlobalOps {

    @Autowired
    PetrichorContext petrichorContext;

    @Autowired
    ContextUtil contextUtil;

    @Override
    @CheckKey
    public ResponseResult<String> delete(String key) {
        PetrichorObject petrichorObject = contextUtil.delete(key);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, petrichorObject.getPetrichorValue().toString());
    }

    @Override
    @CheckKey
    public ResponseResult<Boolean> exist(String key) {
        return null;
    }

    @Override
    @CheckKey
    public ResponseResult<Long> expire(String key, long seconds) {

        return null;
    }

    @Override
    @CheckKey
    public ResponseResult<ObjectEncoding> type(String key) {
        return null;
    }

    @Override
    @CheckKey
    public ResponseResult<Long> time(String key) {
        Optional<Long> expireTime = contextUtil.getExpire(key);
        if (expireTime.isPresent() == false)
            return ResponseResult.successResult(CommonResultCode.SUCCESS, -1L);
        Instant expireInstant = Instant.ofEpochSecond(expireTime.get());
        return ResponseResult.successResult(CommonResultCode.SUCCESS, Duration.between(Instant.now(),expireInstant).getSeconds());
    }
}
