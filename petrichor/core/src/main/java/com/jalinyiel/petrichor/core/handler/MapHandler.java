package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.ops.MapOps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class MapHandler implements MapOps {
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
    public ResponseResult<Integer> mapLength(String key) {
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
