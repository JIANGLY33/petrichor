package com.jalinyiel.petrichor.core.ops;

import com.jalinyiel.petrichor.core.ObjectEncoding;
import com.jalinyiel.petrichor.core.ResponseResult;

public interface GlobalOps {

    /**
     * 删除键值对,返还被删除的键
     *
     * @param key
     * @return
     */
    ResponseResult<String> delete(String key);

    /**
     * 检查键是否存在
     *
     * @param key
     * @return
     */
    ResponseResult<Boolean> exist(String key);

    /**
     * 为键值对设置过期时间
     * 不同返回值的含义为:
     * -大于等于0的整数:剩余的过期时间
     * - -1:键未设置过期时间
     * - -2:键不存在
     *
     * @param key
     * @param seconds
     * @return
     */
    ResponseResult<Integer> expire(String key, long seconds);

    /**
     * 检查指定键的值类型
     *
     * @param key
     * @return
     */
    ResponseResult<ObjectEncoding> type(String key);
}
