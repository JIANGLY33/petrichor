package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.CommonResultCode;
import com.jalinyiel.petrichor.core.ContextUtil;
import com.jalinyiel.petrichor.core.ResponseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.IntStream;

@Aspect
@Component
public class HandlerAspect {

    @Autowired
    ContextUtil contextUtil;

    private final String PARAM_NAME_OF_KEY = "key";

    @Pointcut("@annotation(com.jalinyiel.petrichor.core.check.CheckKey)")
    public void requiredKeyFunc(){}


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
}
