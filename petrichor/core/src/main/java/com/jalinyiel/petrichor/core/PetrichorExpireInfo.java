package com.jalinyiel.petrichor.core;

import lombok.Data;

@Data
public class PetrichorExpireInfo {

    Long expireTime;

    PetrichorObject value;

    public PetrichorExpireInfo(PetrichorObject value, Long expireTime) {
        this.expireTime = expireTime;
        this.value = value;
    }
}
