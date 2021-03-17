package com.jalinyiel.petrichor.start.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BaseInfoSummary implements Serializable {

    private static final long serialVersionUID = 3484943946026298386L;

    /** 系统存储的键值对总数 **/
    private long keyNums;

    /** 系统存储的带过期时间的键值对总数 **/
    private long expireKeyNums;

    /** 系统运行时长 **/
    private long runDuration;

    /** 系统已经处理的任务数量 **/
    private long handledTaskNums;
}
