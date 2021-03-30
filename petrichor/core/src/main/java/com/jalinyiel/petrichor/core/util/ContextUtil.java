package com.jalinyiel.petrichor.core.util;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.collect.PetrichorValue;
import com.jalinyiel.petrichor.core.exception.KeyNotExistException;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Supplier;

@Component
public class ContextUtil<T> {

    @Autowired
    PetrichorContext petrichorContext;

    public <T extends PetrichorValue> T getValue(String key) {
        PetrichorDict dict = getDataDict();
        Optional<PetrichorObject> petrichorValue = dict.getByKey(key);
        T petrichorList = (T) petrichorValue.get().getPetrichorValue();
        return petrichorList;
    }

    public boolean keyExist(String key) {
        PetrichorDict dict = getDataDict();
        return dict.exist(key);
    }

    public PetrichorObject delete(String key) {
        PetrichorDict dict = getDataDict();
        return dict.delete(key);
    }

    private PetrichorDict getDataDict() {
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        PetrichorDict dict = petrichorDb.getKeyValues();
        return dict;
    }

    private ExpireDict getExpireDict() {
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        ExpireDict expireDict = petrichorDb.getExpireKeys();
        return expireDict;
    }

    public long setExpire(PetrichorObject petrichorObject, long time) {
        ExpireDict expireDict = this.getExpireDict();
        return expireDict.put(petrichorObject, time);
    }

    public Optional<Long> getExpire(String key) {
        ExpireDict expireDict = this.getExpireDict();
        return expireDict.get(key);
    }

    /**
     * 移除过期的键
     * @param key
     * @return 0:键尚未过期, -1:键未设置过期时间 其余正数表示键已经过期的时间
     */
    public long removeExpireKey(String key) {
        ExpireDict expireDict = this.getExpireDict();
        long t = expireDict.get(key).orElseThrow(KeyNotExistException::new);
        Instant expireInstant = Instant.ofEpochSecond(t);
        Duration duration = Duration.between(Instant.now(),expireInstant);
        if (!duration.isNegative()) {
            return 0L;
        }
        if(expireDict.remove(key) == -1) return -1;
        return duration.abs().getSeconds();
    }

    public long taskNumIncre() {
        return petrichorContext.curDbTaskIncre();
    }

    public long getTaskNums() {
        return petrichorContext.getCurDbTaskNums();
    }
    public long getKeySize() {
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        return petrichorDb.getKeyValues().size();
    }

    public long getExpireKeySize() {
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        return petrichorDb.getExpireKeys().size();
    }

    public long getRunDuration() {
        Duration duration = Duration.between(petrichorContext.getInitInstant(), Instant.now());
        return duration.getSeconds();
    }

    public void updateTaskRecord(TaskType taskType, String key) {
        petrichorContext.updateTaskRecord(taskType,key);
    }

    public List<PetrichorObject> getHotSpotData() {
        return petrichorContext.getCurrentDb().getHotSpotData();
    }

    public List<PetrichorObject> getExpireData() {
        return petrichorContext.getCurrentDb().getExpireData();
    }

    public Map<TaskType, TreeMap<String,Long>> getDataTypeTaskCount() {
        return petrichorContext.getCurrentDb().getDataTypeTaskCount();
    }
}
