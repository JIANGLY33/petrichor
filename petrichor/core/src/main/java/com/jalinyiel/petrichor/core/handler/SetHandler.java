package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.collect.PetrichorSet;
import com.jalinyiel.petrichor.core.ops.SetOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@DataType(type = TaskType.SET_TASK)
public class SetHandler extends PetrichorHandler implements SetOps {

    @Autowired
    PetrichorContext petrichorContext;

    @Autowired
    ContextUtil<PetrichorSet> contextUtil;

    private final ObjectType VALUE_TYPE = ObjectType.PETRICHOR_SET;

    private final ObjectEncoding VALUE_ENCODING = ObjectEncoding.HASH_SET;

    @Override
    public ResponseResult<Void> setAdd(String key, String... elements) {
        return null;
    }

    @Override
    public ResponseResult<Void> setRemove(String key, String... elements) {
        return null;
    }

    @Override
    public ResponseResult<Integer> setSize(String key) {
        return null;
    }

    @Override
    public ResponseResult<Integer> setIntersect(String... keys) {
        return null;
    }

    @Override
    public ResponseResult<Integer> setUnion(String... keys) {
        return null;
    }

    @Override
    public ResponseResult<Integer> setComplementary(String... keys) {
        return null;
    }
}
