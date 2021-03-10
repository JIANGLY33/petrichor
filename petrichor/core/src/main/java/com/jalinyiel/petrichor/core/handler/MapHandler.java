package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.collect.PetrichorMap;
import com.jalinyiel.petrichor.core.ops.MapOps;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public ResponseResult<Void> mapSet(String key, Map map) {
        return null;
    }

    @Override
    public ResponseResult<String> mapGet(String key, String filed) {
        return null;
    }

    @Override
    public ResponseResult<String> mapDelete(String key, String field) {
        return null;
    }

    @Override
    public ResponseResult<Integer> mapSize(String key) {
        return null;
    }

    @Override
    public ResponseResult<Set<String>> mapKeys(String key) {
        return null;
    }

    @Override
    public ResponseResult<List<String>> mapValues(String key) {
        return null;
    }
}
