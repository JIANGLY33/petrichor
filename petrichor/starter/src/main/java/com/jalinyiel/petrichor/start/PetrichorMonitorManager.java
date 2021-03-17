package com.jalinyiel.petrichor.start;

import com.google.common.collect.ImmutableList;
import com.jalinyiel.petrichor.monitor.BaseInfoVisitor;
import com.jalinyiel.petrichor.start.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetrichorMonitorManager {

    @Autowired
    BaseInfoVisitor baseInfoVisitor;

    public BaseInfoSummary collectBaseInfo() {
        BaseInfoSummary baseInfoSummary = BaseInfoSummary.builder()
                .keyNums(baseInfoVisitor.countKey())
                .expireKeyNums(baseInfoVisitor.countExpireKey())
                .runDuration(baseInfoVisitor.getRunTime())
                .handledTaskNums(baseInfoVisitor.countHandledTask()).build();

        return baseInfoSummary;
    }


    public ExpireKeysInfoSummary collectExpireKeysInfo() {
        List<ExpireKeysInfo> expireKeysInfos = ImmutableList.of();
        return new ExpireKeysInfoSummary(expireKeysInfos);
    }

    public HotSpotDataInfoSummary collectHotSpotDataInfo() {
        return null;
    }

    public MemoryInfoSummary collectMemoryInfo() {
        return null;
    }

    public SlowQueryAnalysisSummary collectSlowQueryAnalysisInfo() {
        return null;
    }

    public TaskInfoSummary collectTaskInfo() {
        return null;
    }
}
