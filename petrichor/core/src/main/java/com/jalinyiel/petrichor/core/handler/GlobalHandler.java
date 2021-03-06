package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.ObjectEncoding;
import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.ops.GlobalOps;
import org.springframework.stereotype.Component;

@Component
public class GlobalHandler implements GlobalOps {
    @Override
    public ResponseResult<String> delete(String key) {
        return null;
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
