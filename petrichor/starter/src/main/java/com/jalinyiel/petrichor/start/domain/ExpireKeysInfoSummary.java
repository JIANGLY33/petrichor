package com.jalinyiel.petrichor.start.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ExpireKeysInfoSummary implements Serializable {

    List<ExpireKeysInfo> keysInfos;

    public ExpireKeysInfoSummary() {
    }

    public ExpireKeysInfoSummary(List<ExpireKeysInfo> keysInfos) {
        this.keysInfos = keysInfos;
    }
}
