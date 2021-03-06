package com.jalinyiel.petrichor.core.task;

public enum SupportedOperation {
    RIGHT_PUSH("rightPush"),
    LIST_LENGTH("listLength");

    private String opsName;

    SupportedOperation(String opsName) {
        this.opsName = opsName;
    }

    public String getOpsName() {
        return opsName;
    }
}
