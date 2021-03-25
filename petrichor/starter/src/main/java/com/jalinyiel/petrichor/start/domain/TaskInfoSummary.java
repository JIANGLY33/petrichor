package com.jalinyiel.petrichor.start.domain;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class TaskInfoSummary implements Serializable {

    private List<String> times;

    private List<Long> stringTaskCounts;

    private List<Long> listTaskCounts;

    private List<Long> setTaskCount;
    
    private List<Long> mapTaskCount;

}
