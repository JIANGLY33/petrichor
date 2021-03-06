package com.jalinyiel.petrichor.core.ops;

import com.jalinyiel.petrichor.core.ResponseResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MapOps {

    ResponseResult<Void> mapSet(String key, Map map);

    ResponseResult<String> mapGet(String key, String filed);

    ResponseResult<String> mapDelete(String key, String field);

    ResponseResult<Integer> mapLength(String key);

    ResponseResult<Set<String>> mapKeys(String key);

    ResponseResult<List<String>> mapValues(String key);
}
