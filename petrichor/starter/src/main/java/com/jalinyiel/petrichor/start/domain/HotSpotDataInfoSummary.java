package com.jalinyiel.petrichor.start.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
public class HotSpotDataInfoSummary implements Serializable {

    List<Double> hotSpotMemories;

    List<String> hotSpotKeys;

    List<Integer> hotSpotQueryTimes;

}
