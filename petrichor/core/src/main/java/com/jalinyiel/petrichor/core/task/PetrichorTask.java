package com.jalinyiel.petrichor.core.task;

import com.jalinyiel.petrichor.core.CommonResultCode;
import com.jalinyiel.petrichor.core.ResponseResult;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PetrichorTask {

    private final String methodName;

    private final Object[] params;

    private final Class[] paramClass;

    private final TaskType type;

    private List<TaskListener> listeners;

    public String getMethodName() {
        return methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public Class[] getParamClass() {
        return paramClass;
    }

    public TaskType getType() {
        return type;
    }

    public List<TaskListener> getListeners() {
        return listeners;
    }

    public PetrichorTask(String methodName, Object[] params, Class[] paramClass, TaskType type) {
        this.methodName = methodName;
        this.params = params;
        this.paramClass = paramClass;
        this.type = type;
        this.listeners = new LinkedList<>();
    }

    public static PetrichorTask of(String methodName, Object[] params, Class[] paramClass, TaskType type) {
        return new PetrichorTask(methodName, params, paramClass, type);
    }

    public ResponseResult<List> call() {
        List data = listeners.stream().map(listener -> listener.process(this)).collect(Collectors.toList());
        return ResponseResult.successResult(CommonResultCode.SUCCESS, data);
    }

}
