package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.CommonResultCode;
import com.jalinyiel.petrichor.core.ContextUtil;
import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.task.TaskType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.stream.IntStream;

@Aspect
@Component
@Slf4j
public class HandlerAspect {

    @Autowired
    ContextUtil contextUtil;

    private final String PARAM_NAME_OF_KEY = "key";

    @Pointcut("@annotation(com.jalinyiel.petrichor.core.check.CheckKey)")
    public void requiredKeyFunc(){}

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.*.*(..))&&execution(public * *(..))&&args(String, ..)")
    public void countTasks(){}

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.ListHandler.*(..))&&execution(public * *(..))&&args(String, ..)")
    public void listTasks(){}

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.StringHandler.*(..))&&execution(public * *(..))&&args(String, ..)")
    public void stringTasks(){}

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.SetHandler.*(..))&&execution(public * *(..))&&args(String, ..)")
    public void setTasks(){}

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.MapHandler.*(..))&&execution(public * *(..))&&args(String, ..)")
    public void mapTasks(){}


    @Around("requiredKeyFunc()")
    public ResponseResult keyExist(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args =  pjp.getArgs();
        String key = (String) args[keyIndex];
        if(!contextUtil.keyExist(key)) {
            return  ResponseResult.failedResult(CommonResultCode.NOT_FOUND,"key not exist!");
        }
        return (ResponseResult) pjp.proceed();
    }

//    @Before("countTasks()")
//    public void allTask(JoinPoint joinPoint) {
//        long taskNums = contextUtil.taskNumIncre();
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        String[] paramNames = methodSignature.getParameterNames();
//        int keyIndex = IntStream.range(0, paramNames.length)
//                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
//        Object[] args =  joinPoint.getArgs();
//        String key = (String) args[keyIndex];
//
//    }

    @After("listTasks()")
    public void listTaskCount(JoinPoint joinPoint) {
        long taskNums = contextUtil.taskNumIncre();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args =  joinPoint.getArgs();
        String key = (String) args[keyIndex];
        contextUtil.updateTaskRecord(TaskType.LIST_TASK,key);
    }

    @After("stringTasks()")
    public void stringTaskCount(JoinPoint joinPoint) {
        long taskNums = contextUtil.taskNumIncre();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args =  joinPoint.getArgs();
        String key = (String) args[keyIndex];
        contextUtil.updateTaskRecord(TaskType.STRING_TASK,key);
    }

    @After("setTasks()")
    public void setTaskCount(JoinPoint joinPoint) {
        long taskNums = contextUtil.taskNumIncre();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args =  joinPoint.getArgs();
        String key = (String) args[keyIndex];
        contextUtil.updateTaskRecord(TaskType.SET_TASK,key);
    }

    @After("mapTasks()")
    public void mapTaskCount(JoinPoint joinPoint) {
        long taskNums = contextUtil.taskNumIncre();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args =  joinPoint.getArgs();
        String key = (String) args[keyIndex];
        contextUtil.updateTaskRecord(TaskType.MAP_TASK,key);
    }
}
