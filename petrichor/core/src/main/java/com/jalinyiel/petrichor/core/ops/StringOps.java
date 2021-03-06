package com.jalinyiel.petrichor.core.ops;

import com.jalinyiel.petrichor.core.ResponseResult;

import java.util.List;

public interface StringOps {

    ResponseResult<Void> set(String key, String value);

    ResponseResult<String> get(String key);

    ResponseResult<Void> setWithSecondsExpire(String key, long seconds, String value);

    ResponseResult<Void> setWithMileSecondsExpire(String key, long mileSeconds, String value);

    ResponseResult<List<String>> multipleGet(String... keys);

}
