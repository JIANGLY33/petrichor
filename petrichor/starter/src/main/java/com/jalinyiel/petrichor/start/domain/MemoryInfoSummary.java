package com.jalinyiel.petrichor.start.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MemoryInfoSummary implements Serializable {

    private long sumMemoryCost;

    private long stringMemory;

    private long listMemory;

    private long setMemory;

    private long mapMemory;


}
