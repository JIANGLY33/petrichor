package com.jalinyiel.petrichor.core.task;

public enum SupportedOperation {

    /** 全局命令 **/
    DELETE_KEY("delete"),

    /** List类型的命令 **/
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
