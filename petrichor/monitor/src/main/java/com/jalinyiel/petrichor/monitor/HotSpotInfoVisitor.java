package com.jalinyiel.petrichor.monitor;

import com.jalinyiel.petrichor.core.PetrichorObject;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import org.apache.lucene.util.RamUsageEstimator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HotSpotInfoVisitor {

    @Autowired
    ContextUtil contextUtil;

    public List<Long> getMemories() {
        //这行无法省略，否则类型擦除后无法调用PetrichorObject的方法
        List<PetrichorObject> hotSpotStatistics = contextUtil.getHotSpotData();
        return hotSpotStatistics.stream().map(o -> {
            PetrichorString key = (PetrichorString) o.getPetrichorValue();
            Optional<PetrichorObject> petrichorValue = contextUtil.getValueIncludeExpire(key.getValue());
            return petrichorValue.isPresent()?RamUsageEstimator.sizeOf(petrichorValue.get()):0L;
        }).collect(Collectors.toList());
    }

    public List<String> getKeys() {
        List<PetrichorObject> hotSpotStatistics = contextUtil.getHotSpotData();
        return hotSpotStatistics.stream().map(o -> {
            PetrichorString key = (PetrichorString) o.getPetrichorValue();
            return key.getValue();
        }).collect(Collectors.toList());
    }

    public List<Long> getQueryTimes() {
        List<PetrichorObject> hotSpotStatistics = contextUtil.getHotSpotData();
        return hotSpotStatistics.stream().map(o->(long)o.getCount()).collect(Collectors.toList());
    }
}
