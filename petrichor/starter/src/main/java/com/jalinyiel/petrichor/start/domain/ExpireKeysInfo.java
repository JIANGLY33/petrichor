package com.jalinyiel.petrichor.start.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ExpireKeysInfo implements Serializable {

    private static final long serialVersionUID = 4189374943846123649L;

    private String keyName;

    private String type;

    private long memory;

    private long visitTimes;

    private String expireTime;
}
