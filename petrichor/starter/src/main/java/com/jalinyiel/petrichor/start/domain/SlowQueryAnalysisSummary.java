package com.jalinyiel.petrichor.start.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class SlowQueryAnalysisSummary implements Serializable {

    List<Double> timeCost;

    List<String> tasks;

    List<Double> runTimes;


}
