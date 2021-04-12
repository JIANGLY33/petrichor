package com.jalinyiel.petrichor.monitor;

import com.jalinyiel.petrichor.core.PetrichorObject;
import com.jalinyiel.petrichor.core.collect.PetrichorString;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SlowQueryAnalysisVisitor {

    @Autowired
    ContextUtil contextUtil;

    public List<String> getKeys() {
        Map<PetrichorObject, Duration> slowQueryStatistic = contextUtil.getSlowQueryStatistic();
        return slowQueryStatistic.entrySet().stream().map(e -> {
            PetrichorString petrichorString = (PetrichorString) e.getKey().getPetrichorValue();
            return petrichorString.getValue();
        }).collect(Collectors.toList());
    }

    public List<Long> getQueryTime() {
        Map<PetrichorObject, Duration> slowQueryStatistic = contextUtil.getSlowQueryStatistic();
        return slowQueryStatistic.entrySet().stream().map(e -> e.getValue().toMillis()).collect(Collectors.toList());
    }

    public List<Integer> getVisitTimes() {
        Map<PetrichorObject, Duration> slowQueryStatistic = contextUtil.getSlowQueryStatistic();
        return slowQueryStatistic.entrySet().stream().map(e -> e.getKey().getCount()).collect(Collectors.toList());
    }
}
