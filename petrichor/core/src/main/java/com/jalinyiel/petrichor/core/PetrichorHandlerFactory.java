package com.jalinyiel.petrichor.core;

import com.jalinyiel.petrichor.core.handler.DataType;
import com.jalinyiel.petrichor.core.handler.PetrichorHandler;
import com.jalinyiel.petrichor.core.task.TaskType;
import com.jalinyiel.petrichor.core.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class PetrichorHandlerFactory {

    @Autowired
    SpringContextUtil springContextUtil;

    private Map<TaskType, PetrichorHandler> petrichorHandlerMap = new HashMap<>();

    @PostConstruct
    public void init(){
        Map<String, Object> beanMap = springContextUtil.getContext().getBeansWithAnnotation(DataType.class);

        for(Object handler : beanMap.values()) {
            DataType dataType =  handler.getClass().getAnnotation(DataType.class);
            petrichorHandlerMap.put(dataType.type(), (PetrichorHandler) handler);
        }
    }

    public PetrichorHandler of(TaskType taskType) {
        return petrichorHandlerMap.get(taskType);
    }
}
