package com.jalinyiel.petrichor.core.util;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.collect.PetrichorEntry;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.collect.PetrichorValue;
import com.jalinyiel.petrichor.core.exception.KeyNotExistException;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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

    public PetrichorObject getKey(String key) {
        PetrichorDict petrichorDict = getDataDict();
        return petrichorDict.getKey(key).orElseThrow(KeyNotExistException::new);
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
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        PetrichorDict petrichorDict = petrichorDb.getKeyValues();
        petrichorDict.delete(key);
        //键过期后更新统计数据
        updateExpireList(key);
        return duration.abs().getSeconds();
    }

    /**
     * 更新过期键的统计信息
     * @param key
     */
    private void updateExpireList(String key) {
        List<Map.Entry<PetrichorObject,PetrichorExpireInfo>> expireStatistics = this.getExpireData();
        if (expireStatistics.size() >= getExpireCapacity()) {
            expireStatistics = expireStatistics.stream().sorted(
                    (e1, e2)-> e1.getValue().getExpireTime().intValue() - e2.getValue().getExpireTime().intValue()
            ).collect(Collectors.toList());
            expireStatistics.remove(0);
        } else {
            PetrichorObject petrichorKey = this.getKey(key);
            Map.Entry<PetrichorObject,PetrichorExpireInfo> e = new PetrichorEntry<>(petrichorKey,
                    new PetrichorExpireInfo(this.getValue(key),Instant.now().getEpochSecond()));
            expireStatistics.add(e);
        }
        replaceExpireStatistics(expireStatistics);
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

    public List<Map.Entry<PetrichorObject, PetrichorExpireInfo>> getExpireData() {
        return petrichorContext.getCurrentDb().getExpireData();
    }

    public Map<TaskType, TreeMap<String,Long>> getDataTypeTaskCount() {
        return petrichorContext.getCurrentDb().getDataTypeTaskCount();
    }

    public int getHotSpotCapacity() {
        return petrichorContext.getCurrentDb().HOT_SPOT_DATA_CAPACITY;
    }

    public int getExpireCapacity() {
        return petrichorContext.getCurrentDb().EXPIRE_KEY_CAPACITY;
    }

    public int getTaskCountCapacity() {
        return petrichorContext.getCurrentDb().TASK_COUNTS_CAPACITY;
    }

    private void replaceExpireStatistics(List<Map.Entry<PetrichorObject, PetrichorExpireInfo>> expireStatistics) {
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        petrichorDb.setExpireData(expireStatistics);
    }

    public void replaceHotSpotStatistics(List<PetrichorObject> hotSpotStatistics) {
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        petrichorDb.setHotSpotData(hotSpotStatistics);
    }

    public Optional<PetrichorObject> getValueIncludeExpire(String key) {
        PetrichorDict dict = getDataDict();
        Optional<PetrichorObject> petrichorValue = dict.getByKey(key);
        if (petrichorValue.isPresent()) {
            return petrichorValue;
        }
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        List<Map.Entry<PetrichorObject,PetrichorExpireInfo>> expireData = petrichorDb.getExpireData();
        Optional<PetrichorExpireInfo> hitData = expireData.stream().filter(e -> {
            PetrichorString keyStr = (PetrichorString) e.getKey().getPetrichorValue();
            return keyStr.equals(key);
        }).map(Map.Entry::getValue).findAny();
        if (hitData.isPresent()) {
            return Optional.of(hitData.get().getValue());
        }
        return Optional.empty();
    }

    public void updateSlowQueryStatistic(String key, Duration duration) {
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        Map<PetrichorObject, Duration> slowQueryStatistic = petrichorDb.getSlowQueryStatistic();
        PetrichorObject petrichorObject = this.getKey(key);
        if (petrichorDb.SLOW_QUERY_CAPACITY <= slowQueryStatistic.size()) {
            slowQueryStatistic = slowQueryStatistic.entrySet().stream().sorted(Comparator.comparing(entry -> entry.getValue().toMillis()))
                    .limit(petrichorDb.SLOW_QUERY_CAPACITY-1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        slowQueryStatistic.put(petrichorObject,duration);
    }

    public Map<PetrichorObject, Duration> getSlowQueryStatistic() {
        return petrichorContext.getCurrentDb().getSlowQueryStatistic();
    }

    public Long getObjectMem(ObjectType objectType) {
        return petrichorContext.getCurrentDb().getTaskMemory(objectType);
    }
}
