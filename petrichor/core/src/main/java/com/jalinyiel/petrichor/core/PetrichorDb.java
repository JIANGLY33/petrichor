package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.collect.PetrichorEntry;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.task.TaskType;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class PetrichorDb {

    private int id;

    private PetrichorDict keyValues;

    //存在过期时间的键
    private ExpireDict expireKeys;

    private AtomicLong taskCount;

    private List<PetrichorObject> hotSpotData;

    //已经过期的键
    private List<Map.Entry<PetrichorObject, PetrichorExpireInfo>> expireData;

    private Map<TaskType, TreeMap<String, Long>> dataTypeTaskCount;

    private Map<PetrichorObject, Duration> slowQueryStatistic;

    public final int HOT_SPOT_DATA_CAPACITY = 10;

    public final int EXPIRE_KEY_CAPACITY = 10;

    public final int TASK_COUNTS_CAPACITY = 10;

    public final int SLOW_QUERY_CAPACITY = 8;

    public PetrichorDb(int id, PetrichorDict keyValues, ExpireDict expireKeys) {
        this.id = id;
        this.keyValues = keyValues;
        this.expireKeys = expireKeys;
        this.taskCount = new AtomicLong(0);
        this.hotSpotData = new ArrayList<>(HOT_SPOT_DATA_CAPACITY);
        this.expireData = new ArrayList<>(EXPIRE_KEY_CAPACITY);
        this.dataTypeTaskCount = new HashMap<>();
        this.slowQueryStatistic = new HashMap<>(SLOW_QUERY_CAPACITY);
        dataTypeTaskCount.put(TaskType.LIST_TASK, new TreeMap<>(this::compare));
        dataTypeTaskCount.put(TaskType.STRING_TASK, new TreeMap<>(this::compare));
        dataTypeTaskCount.put(TaskType.MAP_TASK, new TreeMap<>(this::compare));
        dataTypeTaskCount.put(TaskType.SET_TASK, new TreeMap<>(this::compare));
        dataTypeTaskCount.put(TaskType.GLOBAL_TASK, new TreeMap<>(this::compare));
    }

    public int getId() {
        return id;
    }

    public PetrichorDict getKeyValues() {
        return keyValues;
    }

    public ExpireDict getExpireKeys() {
        return expireKeys;
    }

    public AtomicLong getTaskCount() {
        return taskCount;
    }

    public long taskCountIncre() {
        return taskCount.incrementAndGet();
    }

    public long dataTypeCountIncre(TaskType taskType) {
        TreeMap<String, Long> value = dataTypeTaskCount.entrySet().stream()
                .filter(taskTypeListEntry -> taskTypeListEntry.getKey().equals(taskType))
                .map(Map.Entry::getValue)
                .findAny().get();
        String key = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"));
        Long count = value.get(key);
        value.put(key, null == count ? 1 : count + 1);
        if (value.size() > TASK_COUNTS_CAPACITY) value.pollFirstEntry();
//        value.entrySet().stream()
//                .forEach(integerLongEntry
//                        -> System.out.println(String.format("key is %s, value is %d", integerLongEntry.getKey(), integerLongEntry.getValue())));
        return value.get(key);
    }

    public List<PetrichorObject> getHotSpotData() {
        List<PetrichorObject> petrichorObjects = this.hotSpotData;
        return petrichorObjects;
    }

    public List<Map.Entry<PetrichorObject,PetrichorExpireInfo>> getExpireData() {
        return expireData;
    }

    public Map<TaskType, TreeMap<String, Long>> getDataTypeTaskCount() {
        return dataTypeTaskCount;
    }

    private int compare(String t1, String t2) {
        LocalTime localTime1 = LocalTime.parse(t1);
        LocalTime localTime2 = LocalTime.parse(t2);
        return localTime1.compareTo(localTime2);
    }

    public void setHotSpotData(List<PetrichorObject> hotSpotData) {
        this.hotSpotData = hotSpotData;
    }

    public void setExpireData(List<Map.Entry<PetrichorObject, PetrichorExpireInfo>> expireData) {
        this.expireData = expireData;
    }

    public Map<PetrichorObject, Duration> getSlowQueryStatistic() {
        return slowQueryStatistic;
    }

    public void setSlowQueryStatistic(Map<PetrichorObject, Duration> slowQueryStatistic) {
        this.slowQueryStatistic = slowQueryStatistic;
    }

    public List<Map.Entry<PetrichorObject,PetrichorExpireInfo>> removeExpire() {
        Map<PetrichorObject, PetrichorObject> keyValues = getKeyValues().getDict();
        Iterator<Map.Entry<PetrichorObject,PetrichorObject>> iterator = keyValues.entrySet().iterator();
        List<Map.Entry<PetrichorObject,PetrichorExpireInfo>> res = new LinkedList<>();
        while(iterator.hasNext()) {
            Map.Entry<PetrichorObject,PetrichorObject> entry = iterator.next();
            PetrichorObject key = entry.getKey();
            Optional<Long> expireTime = expireKeys.get(key);
            if (expireTime.isPresent() && expireTime.get() <= Instant.now().getEpochSecond()) {
                res.add(new PetrichorEntry<>(key,new PetrichorExpireInfo(keyValues.get(key),expireTime.get())));
                iterator.remove();
                expireKeys.remove(key);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        PetrichorObject p = PetrichorObjectFactory.of(ObjectType.PETRICHOR_STRING,ObjectEncoding.RAW_STRING, new PetrichorString("a"));
        HashMap<PetrichorObject,Long> h  = new HashMap<>();
        h.put(p,12L);
        PetrichorObject q = PetrichorObjectFactory.of(ObjectType.PETRICHOR_STRING,ObjectEncoding.RAW_STRING, new PetrichorString("a"));
        Long l = h.get(q);
        System.out.println(q.equals(p));
    }
}
