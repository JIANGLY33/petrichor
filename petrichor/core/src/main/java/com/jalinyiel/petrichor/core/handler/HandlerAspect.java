package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.CommonResultCode;
import com.jalinyiel.petrichor.core.ContextUtil;
import com.jalinyiel.petrichor.core.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.*.*(..))&&execution(public * *(..))")
    public void allTasks(){}


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

    @Before("allTasks()")
    public void allTask(JoinPoint joinPoint) {
        long taskNums = contextUtil.taskNumIncre();
//        log.info(String.format("%s is executing,task num is %d.",joinPoint.getSignature(),taskNums));
    }
}
