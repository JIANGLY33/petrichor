package com.jalinyiel.petrichor.core;

/**
 * Petrichor对象的具体类型
 *
 * @author Jalinyiel
 * @Date 2021.2.22
 */
public enum ObjectType {

    /**
     * 字符类型
     */
    PETRICHOR_STRING("string", (byte) 0),
    /**
     * 列表类型
     */
    PETRICHOR_LIST("list", (byte) 1),
    /**
     * 散列表类型
     */
    PETRICHOR_MAP("map", (byte) 2),
    /**
     * 集合类型
     */
    PETRICHOR_SET("set", (byte) 3),
    /**
     * 有序集合类型
     */
    PETRICHOR_ZSET("zset", (byte) 4);

    private String name;

    private byte bit;

    ObjectType(String name, byte bit) {
        this.name = name;
        this.bit = bit;
    }

    public String getName() {
        return name;
    }

    public byte getBit() {
        return bit;
    }
}
