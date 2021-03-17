package com.jalinyiel.petrichor.start.domain;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class TaskInfoSummary implements Serializable {

    private List<Integer> stringTaskCounts;

    private List<Integer> listTaskCounts;

    private List<Integer> setTaskCount;
    
    private List<Integer> mapTaskCount;

}
