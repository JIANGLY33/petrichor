package com.jalinyiel.petrichor.start.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class SlowQueryAnalysisSummary implements Serializable {

    private static final long serialVersionUID = -5343609562921277382L;

    List<Long> timeCost;

    List<String> tasks;

    List<Integer> runTimes;


}
