package com.jalinyiel.petrichor.start.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemoryInfoSummary implements Serializable {

    private long sumMemory;

    private long stringMemory;

    private long listMemory;

    private long setMemory;

    private long mapMemory;
}
