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
        HotSpotDataInfoSummary hotSpotDataInfoSummary = HotSpotDataInfoSummary.builder()
                .hotSpotKeys(ImmutableList.of())
                .hotSpotMemories(ImmutableList.of())
                .hotSpotQueryTimes(ImmutableList.of())
                .build();
        return hotSpotDataInfoSummary;
    }

    public MemoryInfoSummary collectMemoryInfo() {
        MemoryInfoSummary memoryInfoSummary = MemoryInfoSummary.builder()
                .setMemory(0)
                .mapMemory(0)
                .listMemory(0)
                .stringMemory(0)
                .sumMemoryCost(0)
                .build();
        return memoryInfoSummary;
    }

    public SlowQueryAnalysisSummary collectSlowQueryAnalysisInfo() {
        SlowQueryAnalysisSummary slowQueryAnalysisSummary = SlowQueryAnalysisSummary.builder()
                .tasks(ImmutableList.of())
                .runTimes(ImmutableList.of())
                .timeCost(ImmutableList.of())
                .build();
        return slowQueryAnalysisSummary;
    }

    public TaskInfoSummary collectTaskInfo() {

        TaskInfoSummary taskInfoSummary = TaskInfoSummary.builder()
                .mapTaskCount(ImmutableList.of())
                .listTaskCounts(ImmutableList.of())
                .stringTaskCounts(ImmutableList.of())
                .setTaskCount(ImmutableList.of())
                .times(ImmutableList.of())
                .build();
        return taskInfoSummary;
    }
}
