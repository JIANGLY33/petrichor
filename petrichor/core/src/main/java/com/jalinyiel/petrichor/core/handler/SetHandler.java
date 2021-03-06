package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.ops.SetOps;
import org.springframework.stereotype.Component;

@Component
public class SetHandler implements SetOps {
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
