package com.jalinyiel.petrichor.start.domain;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Builder
@Data
public class MonitorInfoSummary implements Serializable {

    private static final long serialVersionUID = 4572544034946469895L;

    /** 系统基本信息的汇总 **/
    private BaseInfoSummary baseInfoSummary;

    /** 过期数据信息的汇总 **/
    private ExpireKeysInfoSummary expireKeysInfoSummary;

    /** 内存相关信息的汇总 **/
    private MemoryInfoSummary memoryInfoSummary;

    /** 热点数据相关信息的汇总 **/
    private HotSpotDataInfoSummary hotSpotDataInfoSummary;

    /** 慢查询信息的汇总 **/
    private SlowQueryAnalysisSummary slowQueryAnalysisSummary;

    /** 执行任务的相关信息汇总 **/
    private TaskInfoSummary taskInfoSummary;

}
