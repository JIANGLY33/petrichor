package com.jalinyiel.petrichor.start;

import com.google.common.collect.ImmutableList;
import com.jalinyiel.petrichor.core.task.TaskType;
import com.jalinyiel.petrichor.domain.ExpireKeysInfo;
import com.jalinyiel.petrichor.monitor.*;
import com.jalinyiel.petrichor.start.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PetrichorMonitorManager {

    @Autowired
    BaseInfoVisitor baseInfoVisitor;

    @Autowired
    ExpireKeyInfoVisitor expireKeyInfoVisitor;

    @Autowired
    MemoryInfoVisitor memoryInfoVisitor;

    @Autowired
    SlowQueryAnalysisVisitor slowQueryAnalysisVisitor;

    @Autowired
    TaskInfoVisitor taskInfoVisitor;

    @Autowired
    HotSpotInfoVisitor hotSpotInfoVisitor;

    public final int TASK_INFO_CAPACITY = 10;

    public BaseInfoSummary collectBaseInfo() {
        BaseInfoSummary baseInfoSummary = BaseInfoSummary.builder()
                .keyNums(baseInfoVisitor.countKey())
                .expireKeyNums(baseInfoVisitor.countExpireKey())
                .runDuration(baseInfoVisitor.getRunTime())
                .handledTaskNums(baseInfoVisitor.countHandledTask()).build();

        return baseInfoSummary;
    }


    public ExpireKeysInfoSummary collectExpireKeysInfo() {
        List<ExpireKeysInfo> expireKeysInfos = expireKeyInfoVisitor.getExpireKeyInfos();
        return new ExpireKeysInfoSummary(expireKeysInfos);
    }

    public HotSpotDataInfoSummary collectHotSpotDataInfo() {
        HotSpotDataInfoSummary hotSpotDataInfoSummary = HotSpotDataInfoSummary.builder()
                .hotSpotKeys(hotSpotInfoVisitor.getKeys())
                .hotSpotMemories(hotSpotInfoVisitor.getMemories())
                .hotSpotQueryTimes(hotSpotInfoVisitor.getQueryTimes())
                .build();
        return hotSpotDataInfoSummary;
    }

    public MemoryInfoSummary collectMemoryInfo() {
        MemoryInfoSummary memoryInfoSummary = MemoryInfoSummary.builder()
                .setMemory(memoryInfoVisitor.getSetMem())
                .mapMemory(memoryInfoVisitor.getMapMem())
                .listMemory(memoryInfoVisitor.getListMem())
                .stringMemory(memoryInfoVisitor.getStringMem())
                .sumMemoryCost(memoryInfoVisitor.getSumMem())
                .build();
        return memoryInfoSummary;
    }

    public SlowQueryAnalysisSummary collectSlowQueryAnalysisInfo() {
        SlowQueryAnalysisSummary slowQueryAnalysisSummary = SlowQueryAnalysisSummary.builder()
                .tasks(slowQueryAnalysisVisitor.getKeys())
                .runTimes(slowQueryAnalysisVisitor.getVisitTimes())
                .timeCost(slowQueryAnalysisVisitor.getQueryTime())
                .build();
        return slowQueryAnalysisSummary;
    }

    public TaskInfoSummary collectTaskInfo() {
        TaskInfoSummary taskInfoSummary = TaskInfoSummary.builder()
                .mapTaskCount(paddingTaskCount(taskInfoVisitor.getTaskCount(TaskType.MAP_TASK)))
                .listTaskCounts(paddingTaskCount(taskInfoVisitor.getTaskCount(TaskType.LIST_TASK)))
                .stringTaskCounts(paddingTaskCount(taskInfoVisitor.getTaskCount(TaskType.STRING_TASK)))
                .setTaskCount(paddingTaskCount(taskInfoVisitor.getTaskCount(TaskType.SET_TASK)))
                .times(paddingTimes())
                .build();
        return taskInfoSummary;
    }

    private List<String> paddingTimes() {
        List<String> validTimes = taskInfoVisitor.getTimes();
        int padSize = TASK_INFO_CAPACITY == validTimes.size() ? 0 : TASK_INFO_CAPACITY - validTimes.size();
        Optional<String> earliestTime = validTimes.stream().findAny();

        List<String> paddingTimes = IntStream.range(0, padSize).boxed().map(integer -> {
            LocalTime baseTime = earliestTime.isPresent() ? LocalTime.parse(earliestTime.get()) : LocalTime.now();
            LocalTime shiftTime = baseTime.minusMinutes(padSize-1-integer);
            return shiftTime.format(DateTimeFormatter.ofPattern("hh:mm"));
        }).collect(Collectors.toList());
        if (validTimes.size() > 0) paddingTimes.addAll(validTimes);
        return paddingTimes;
    }

    private List<Long> paddingTaskCount(List<Long>validTaskCount) {
        int padSize = TASK_INFO_CAPACITY == validTaskCount.size() ? 0 : TASK_INFO_CAPACITY - validTaskCount.size();

        List<Long> paddingTimes = IntStream.range(0, padSize).boxed().map(i -> 0L).collect(Collectors.toList());
        if (validTaskCount.size() > 0) paddingTimes.addAll(validTaskCount);
        return paddingTimes;
    }
}
