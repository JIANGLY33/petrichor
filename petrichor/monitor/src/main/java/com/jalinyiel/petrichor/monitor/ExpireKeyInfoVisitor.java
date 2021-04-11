package com.jalinyiel.petrichor.monitor;

import com.jalinyiel.petrichor.core.PetrichorExpireInfo;
import com.jalinyiel.petrichor.core.PetrichorObject;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.collect.PetrichorValue;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import com.jalinyiel.petrichor.domain.ExpireKeysInfo;
import org.apache.lucene.util.RamUsageEstimator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ExpireKeyInfoVisitor {

    @Autowired
    ContextUtil contextUtil;

    public List<ExpireKeysInfo> getExpireKeyInfos() {
        List<Map.Entry<PetrichorObject, PetrichorExpireInfo>> entries = contextUtil.getExpireData();
        return entries.stream().map(e-> {
            PetrichorObject keyObject = e.getKey();
            PetrichorString keyValue = (PetrichorString) keyObject.getPetrichorValue();
            PetrichorExpireInfo expireInfo = e.getValue();
            long expireTime = e.getValue().getExpireTime();
            return ExpireKeysInfo.builder().keyName(keyValue.getValue())
                    .memory(RamUsageEstimator.sizeOf(expireInfo.getValue()))
                    .visitTimes(keyObject.getCount())
                    .type("List")
                    .expireTime(LocalDateTime.ofEpochSecond(expireTime,0,
                            ZoneOffset.ofHours(8)).format(DateTimeFormatter.ISO_DATE_TIME))
                    .build();
        }).collect(Collectors.toList());
    }
}
