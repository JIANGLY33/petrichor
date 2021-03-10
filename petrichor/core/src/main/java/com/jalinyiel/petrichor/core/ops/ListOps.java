package com.jalinyiel.petrichor.core.ops;

import com.jalinyiel.petrichor.core.ResponseResult;

import java.util.List;

public interface ListOps {

    ResponseResult<Void> rightPush(String key, String... values);

    ResponseResult<Void> leftPush(String key, String... values);

    ResponseResult<Void> listInsert(String key, boolean isBefore, int pivot, String value);

    ResponseResult<String> listIndex(String key, int index);

    ResponseResult<String> listRange(String key, int start, int end);

    ResponseResult<Integer> listLength(String key);

    ResponseResult<String> leftPop(String key);

    ResponseResult<String> rightPop(String key);

    ResponseResult<String> listSet(String key, int index, String value);

    ResponseResult<String> listTrim(String key, int start, int end);

}
