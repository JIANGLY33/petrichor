package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.task.TaskType;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class PetrichorDb {

    private int id;

    private PetrichorDict keyValues;

    private ExpireDict expireKeys;

    private AtomicLong taskCount;

    private List<PetrichorObject> hotSpotData;

    private List<PetrichorObject> expireData;

    private Map<TaskType, TreeMap<String, Long>> dataTypeTaskCount;

    private int HOT_SPOT_DATA_CAPACITY = 10;

    private int EXPIRE_KEY_CAPACITY = 10;

    private int TASK_COUNTS_CAPACITY = 10;

    public PetrichorDb(int id, PetrichorDict keyValues, ExpireDict expireKeys) {
        this.id = id;
        this.keyValues = keyValues;
        this.expireKeys = expireKeys;
        this.taskCount = new AtomicLong(0);
        this.hotSpotData = new ArrayList<>(HOT_SPOT_DATA_CAPACITY);
        this.expireData = new ArrayList<>(EXPIRE_KEY_CAPACITY);
        this.dataTypeTaskCount = new HashMap<>();
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
        value.entrySet().stream()
                .forEach(integerLongEntry
                        -> System.out.println(String.format("key is %s, value is %d", integerLongEntry.getKey(), integerLongEntry.getValue())));
        return value.get(key);
    }

    public List<PetrichorObject> getHotSpotData() {
        return hotSpotData;
    }

    public List<PetrichorObject> getExpireData() {
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
}
