package com.jalinyiel.petrichor.start.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SlowQueryAnalysisSummary implements Serializable {

    List<Double> timeCosts;

    List<String> keys;

    List<Double> runTimes;


}
