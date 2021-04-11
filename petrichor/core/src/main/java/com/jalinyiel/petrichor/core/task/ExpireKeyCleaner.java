package com.jalinyiel.petrichor.core.task;

import com.jalinyiel.petrichor.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
@EnableAsync
@DependsOn("petrichorContext")
public class ExpireKeyCleaner {

    @Autowired
    PetrichorContext petrichorContext;

    @Scheduled(fixedRate = 1000)
    public void clean() {
        PetrichorDb petrichorDb = petrichorContext.getCurrentDb();
        List<Map.Entry<PetrichorObject,PetrichorExpireInfo>> expireElement = petrichorDb.removeExpire();
        List<Map.Entry<PetrichorObject,PetrichorExpireInfo>> curElements = petrichorDb.getExpireData();
        curElements.addAll(expireElement);
        petrichorDb.setExpireData(curElements);
    }
}
