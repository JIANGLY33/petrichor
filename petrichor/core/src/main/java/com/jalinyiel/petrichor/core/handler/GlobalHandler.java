package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.check.CheckKey;
import com.jalinyiel.petrichor.core.ops.GlobalOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public ResponseResult<Boolean> exist(String key) {
        return null;
    }

    @Override
    public ResponseResult<Integer> expire(String key, long seconds) {
        return null;
    }

    @Override
    public ResponseResult<ObjectEncoding> type(String key) {
        return null;
    }
}
