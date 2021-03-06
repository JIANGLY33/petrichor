package com.jalinyiel.petrichor.core.ops;

import com.jalinyiel.petrichor.core.ResponseResult;

public interface SetOps {

    ResponseResult<Void> setAdd(String key, String... elements);

    ResponseResult<Void> setRemove(String key, String... elements);

    ResponseResult<Integer> setSize(String key);

    ResponseResult<Integer> setIntersect(String... keys);

    ResponseResult<Integer> setUnion(String... keys);

    ResponseResult<Integer> setComplementary(String... keys);
}
