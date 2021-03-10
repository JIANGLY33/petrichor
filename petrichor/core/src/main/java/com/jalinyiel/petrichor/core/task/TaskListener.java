package com.jalinyiel.petrichor.core.task;

import com.jalinyiel.petrichor.core.CommonResultCode;
import com.jalinyiel.petrichor.core.PetrichorHandlerFactory;
import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.handler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

@Slf4j
@Component
public class TaskListener implements PetrichorListener<ResponseResult>{

    @Autowired
    PetrichorHandlerFactory petrichorHandlerFactory;

    @Override
    public ResponseResult process(PetrichorTask petrichorTask) {
        Optional<Method> methodOptional = Optional.empty();
        Optional<ResponseResult> responseResultOptional = Optional.empty();
        try {
            switch (petrichorTask.getType()) {
                case MAP_TASK:
                    methodOptional = Optional.ofNullable(MapHandler.class
                            .getMethod(petrichorTask.getMethodName(),petrichorTask.getParamClass()));
                    break;
                case SET_TASK:
                    methodOptional = Optional.ofNullable(SetHandler.class
                            .getMethod(petrichorTask.getMethodName(),petrichorTask.getParamClass()));
                    break;
                case LIST_TASK:
                    methodOptional = Optional.ofNullable(ListHandler.class
                            .getMethod(petrichorTask.getMethodName(),petrichorTask.getParamClass()));
                    break;
                case GLOBAL_TASK:
                    methodOptional = Optional.ofNullable(GlobalHandler.class
                            .getMethod(petrichorTask.getMethodName(),petrichorTask.getParamClass()));
                    break;
                case STRING_TASK:
                    methodOptional = Optional.ofNullable(StringHandler.class
                            .getMethod(petrichorTask.getMethodName(),petrichorTask.getParamClass()));
                    break;
            }
            Method method = methodOptional.get();
            Object[] params = petrichorTask.getParams();
            PetrichorHandler handler = petrichorHandlerFactory.of(petrichorTask.getType());
            return (ResponseResult) method.invoke(handler, petrichorTask.getParams());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("TaskListener process exception|",e);
            return ResponseResult.failedResult(CommonResultCode.EXCEPTION,"invalid command!");
        }
    }

}
