package com.jalinyiel.petrichor.core;

/**
 * Petrichor对象的底层编码(具体实现的数据结构)
 *
 * @author Jalinyiel
 * @Date 2021.2.22
 */
public enum ObjectEncoding {

    NUMBER((byte)0),
    RAW_STRING((byte)1),
    LINKED_LIST((byte)2),
    HASH_MAP((byte)3),
    HASH_SET((byte)4),
    TREE_SET((byte)5);

    private byte bit;

    ObjectEncoding(byte bit) {
        this.bit = bit;
    }

    public byte getBit() {
        return bit;
    }
}
