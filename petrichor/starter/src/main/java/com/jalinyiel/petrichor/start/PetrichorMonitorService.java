package com.jalinyiel.petrichor.start;

import com.jalinyiel.petrichor.start.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetrichorMonitorService {

    @Autowired
    PetrichorMonitorManager petrichorMonitorManager;

    public MonitorInfoSummary collectMonitorInfoSummary() {
        BaseInfoSummary baseInfoSummary = petrichorMonitorManager.collectBaseInfo();
        ExpireKeysInfoSummary expireKeysInfoSummary = petrichorMonitorManager.collectExpireKeysInfo();
        HotSpotDataInfoSummary hotSpotDataInfoSummary = petrichorMonitorManager.collectHotSpotDataInfo();
        MemoryInfoSummary memoryInfoSummary = petrichorMonitorManager.collectMemoryInfo();
        SlowQueryAnalysisSummary slowQueryAnalysisSummary = petrichorMonitorManager.collectSlowQueryAnalysisInfo();
        TaskInfoSummary taskInfoSummary = petrichorMonitorManager.collectTaskInfo();
        return MonitorInfoSummary.builder()
                .baseInfoSummary(baseInfoSummary)
                .expireKeysInfoSummary(expireKeysInfoSummary)
                .hotSpotDataInfoSummary(hotSpotDataInfoSummary)
                .memoryInfoSummary(memoryInfoSummary)
                .slowQueryAnalysisSummary(slowQueryAnalysisSummary)
                .taskInfoSummary(taskInfoSummary)
                .build();

    }
}
