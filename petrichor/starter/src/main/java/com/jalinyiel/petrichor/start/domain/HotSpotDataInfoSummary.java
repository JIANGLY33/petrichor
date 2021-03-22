package com.jalinyiel.petrichor.start.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HotSpotDataInfoSummary implements Serializable {

    List<Double> memories;

    List<String> keys;

    List<Integer> queryTimes;
}
