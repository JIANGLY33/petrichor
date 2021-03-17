package com.jalinyiel.petrichor.start.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExpireKeysInfo implements Serializable {
    private String keyName;

    private String type;

    private long memory;

    private long visitTimes;

    private String expireTime;
}
