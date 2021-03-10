package com.jalinyiel.petrichor.core.ops;

import com.jalinyiel.petrichor.core.ResponseResult;

public interface SetOps {

    ResponseResult<Void> setAdd(String key, String... elements);

    ResponseResult<Void> setRemove(String key, String... elements);

    ResponseResult<Integer> setSize(String key);

    ResponseResult<String> setIntersect(String... keys);

    ResponseResult<String> setUnion(String... keys);

    ResponseResult<String> setComplementary(String... keys);

    ResponseResult<String> setGet(String key);
}
