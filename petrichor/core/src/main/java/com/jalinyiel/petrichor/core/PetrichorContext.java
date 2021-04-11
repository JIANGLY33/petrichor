package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@Component
public class PetrichorContext {

    @Value("${petrichor.default_db_id}")
    private int currentDbId;

    private List<PetrichorDb> petrichorDbs;

    private Instant initInstant;

    public PetrichorContext() {
        petrichorDbs = new LinkedList<>();
        initInstant = Instant.now();
    }

    @PostConstruct
    public void init() {
        // 1.读取外部配置文件
        // 2.读取持久化文件来恢复数据
        // 3.根据外部配置初始化空的db
        petrichorDbs.add(PetrichorDbFactory.of(currentDbId));
    }

    public PetrichorDb getCurrentDb() {
        return petrichorDbs.get(currentDbId);
    }

    public Instant getInitInstant() {
        return initInstant;
    }

    public long curDbTaskIncre() {
        PetrichorDb petrichorDb = petrichorDbs.get(currentDbId);
        return petrichorDb.taskCountIncre();
    }

    public long getCurDbTaskNums() {
        PetrichorDb petrichorDb = petrichorDbs.get(currentDbId);
        return petrichorDb.getTaskCount().get();
    }

    public void updateTaskRecord(TaskType taskType, String key) {
        PetrichorDb petrichorDb = this.getCurrentDb();
        PetrichorDict petrichorDict = petrichorDb.getKeyValues();
        petrichorDb.taskCountIncre();
        petrichorDict.keyTaskIncre(key);
        petrichorDb.dataTypeCountIncre(taskType);
    }
}
