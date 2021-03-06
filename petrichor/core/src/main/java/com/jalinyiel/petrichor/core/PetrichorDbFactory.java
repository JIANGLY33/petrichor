package com.jalinyiel.petrichor.core;

import java.util.HashMap;

public class PetrichorDbFactory {
    public static PetrichorDb of(int id) {
        return new PetrichorDb(id, new PetrichorDict(), new ExpireDict());
    }

    public static PetrichorDb of(int id, PetrichorDict petrichorDict, ExpireDict expireDict) {
        return new PetrichorDb(id, petrichorDict, expireDict);
    }
}
