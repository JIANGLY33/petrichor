package com.jalinyiel.petrichor.core.task;

import com.jalinyiel.petrichor.core.CommonResultCode;
import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

@Component
public class TaskListener implements PetrichorListener<ResponseResult>{

    @Autowired
    GlobalHandler globalHandler;

    @Autowired
    ListHandler listHandler;

    @Autowired
    StringHandler stringHandler;

    @Autowired
    SetHandler setHandler;

    @Autowired
    MapHandler mapHandler;

    @Override
    public ResponseResult process(PetrichorTask petrichorTask) {
        Optional<Method> methodOptional = Optional.empty();
        Optional<ResponseResult> responseResultOptional = Optional.empty();
        try {
            switch (petrichorTask.getType()) {
                case MAP_TASK:
                    methodOptional = Optional.ofNullable(mapHandler.getClass()
                            .getMethod(petrichorTask.getMethodName(),petrichorTask.getParamClass()));
                    break;
                case SET_TASK:
                    methodOptional = Optional.ofNullable(setHandler.getClass()
                            .getMethod(petrichorTask.getMethodName(),petrichorTask.getParamClass()));
                    break;
                case LIST_TASK:
                    methodOptional = Optional.ofNullable(listHandler.getClass()
                            .getMethod(petrichorTask.getMethodName(),petrichorTask.getParamClass()));
                    break;
                case GLOBAL_TASK:
                    methodOptional = Optional.ofNullable(globalHandler.getClass()
                            .getMethod(petrichorTask.getMethodName(),petrichorTask.getParamClass()));
                    break;
                case STRING_TASK:
                    methodOptional = Optional.ofNullable(stringHandler.getClass()
                            .getMethod(petrichorTask.getMethodName(),petrichorTask.getParamClass()));
                    break;
            }
            if (!methodOptional.isPresent()) {
                //TODO 处理方法不存在的情况
            }
            Method method = methodOptional.get();
            Optional.ofNullable(method.invoke(petrichorTask.getParams()));
        } catch (NoSuchMethodException noSuchMethodException) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {
        }

        return responseResultOptional.orElse(ResponseResult.failedResult(CommonResultCode.EXCEPTION));
    }

}
