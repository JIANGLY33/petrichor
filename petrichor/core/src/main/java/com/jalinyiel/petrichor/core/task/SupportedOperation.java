package com.jalinyiel.petrichor.core.task;

public enum SupportedOperation {

    /** 全局命令 **/
    DELETE_KEY("delete"),
    KEY_EXIST("exist"),
    EXPIRE_KEY("expire"),
    TYPE_KEY("type"),
    TIME_KEY("time"),

    /** String类型的命令 **/
    STRING_SET("set"),
    STRING_GET("get"),
    STRING_SET_SECONDS_EXPIRE("setWithSecondsExpire"),
    STRING_SET_MILE_SECONDS_EXPIRE("setWithMileSecondsExpire"),
    STRING_MULTIPLE_GET("multipleGet"),

    /** List类型的命令 **/
    RIGHT_PUSH("rightPush"),
    LEFT_PUSH("leftPush"),
    LIST_INSERT("listInsert"),
    LIST_INDEX("listIndex"),
    LIST_RANGE("listRange"),
    LEFT_POP("leftPop"),
    RIGHT_POP("rightPop"),
    LIST_SET("listSet"),
    LIST_TRIM("listTrim"),
    LIST_LENGTH("listLength"),

    /** Set类型的命令 **/
    SET_ADD("setAdd"),
    SET_REMOVE("setRemove"),
    SET_GET("setGet"),
    SET_SIZE("setSize"),
    INTERSECT("setIntersect"),
    UNION("setUnion"),
    COMPLEMENTARY("setComplementary"),

    /** Map类型的命令 **/
    MAP_SET("mapSet"),
    MAP_GET("mapGet"),
    MAP_DELETE("mapDelete"),
    MAP_SIZE("mapSize"),
    MAP_KEYS("mapKeys"),
    MAP_VALUES("mapValues");

    private String opsName;

    SupportedOperation(String opsName) {
        this.opsName = opsName;
    }

    public String getOpsName() {
        return opsName;
    }
}
