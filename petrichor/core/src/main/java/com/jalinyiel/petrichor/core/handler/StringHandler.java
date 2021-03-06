package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.ops.StringOps;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StringHandler implements StringOps {
    @Override
    public ResponseResult<Void> set(String key, String value) {
        return null;
    }

    @Override
    public ResponseResult<String> get(String key) {
        return null;
    }

    @Override
    public ResponseResult<Void> setWithSecondsExpire(String key, long seconds, String value) {
        return null;
    }

    @Override
    public ResponseResult<Void> setWithMileSecondsExpire(String key, long mileSeconds, String value) {
        return null;
    }

    @Override
    public ResponseResult<List<String>> multipleGet(String... keys) {
        return null;
    }
}
