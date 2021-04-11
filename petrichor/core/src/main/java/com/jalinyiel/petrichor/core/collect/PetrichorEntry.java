package com.jalinyiel.petrichor.core.collect;

import java.util.Map;

public final class PetrichorEntry<K,V> implements Map.Entry<K,V>{

    private final K key;

    private V value;

    public PetrichorEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }
}
