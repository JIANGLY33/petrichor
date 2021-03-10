package com.jalinyiel.petrichor.core.ops;

import com.jalinyiel.petrichor.core.ResponseResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MapOps {

    ResponseResult<Void> mapSet(String key, String entryKey, String entryValue);

    ResponseResult<String> mapGet(String key, String filed);

    ResponseResult<String> mapDelete(String key, String field);

    ResponseResult<Integer> mapSize(String key);

    ResponseResult<String> mapKeys(String key);

    ResponseResult<String> mapValues(String key);
}
