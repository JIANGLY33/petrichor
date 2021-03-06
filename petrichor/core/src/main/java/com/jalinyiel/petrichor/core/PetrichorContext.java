package com.jalinyiel.petrichor.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Component
public class PetrichorContext {

    @Value("${petrichor.default_db_id}")
    private int currentDbId;

    private List<PetrichorDb> petrichorDbs;

    public PetrichorContext() {
        petrichorDbs = new LinkedList<>();
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

}
