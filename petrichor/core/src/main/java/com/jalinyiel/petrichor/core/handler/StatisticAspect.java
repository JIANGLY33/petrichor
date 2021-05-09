package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

@Order(-1)
@Aspect
@Component
@Slf4j
public class StatisticAspect {

    @Autowired
    ContextUtil contextUtil;

    private final String PARAM_NAME_OF_KEY = "key";

    @Pointcut("except() && contain()" )
    public void countTasks() {
    }

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.*.*(..))")
    public void contain() {}

    @Pointcut("!execution(* com.jalinyiel.petrichor.core.handler.GlobalHandler.delete(..))")
    public void except() {}

    @Around("countTasks()")
    public ResponseResult slowQueryCount(ProceedingJoinPoint joinPoint) throws Throwable {
        //从方法签名的实参中取出key
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args = joinPoint.getArgs();
        String key = (String) args[keyIndex];
        Instant before = Instant.now();
        ResponseResult responseResult = (ResponseResult) joinPoint.proceed();
        Instant after = Instant.now();
        Duration duration = Duration.between(before,after);
        if(duration.toMillis() > contextUtil.getSlowQueryLimit())
            contextUtil.updateSlowQueryStatistic(key,duration);
        return responseResult;
    }
}
