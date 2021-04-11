package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.*;
import com.jalinyiel.petrichor.core.collect.PetrichorList;
import com.jalinyiel.petrichor.core.util.ContextUtil;
import com.jalinyiel.petrichor.core.task.TaskType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
@Slf4j
public class HandlerAspect {

    @Autowired
    ContextUtil contextUtil;

    private final String PARAM_NAME_OF_KEY = "key";

    @Pointcut("@annotation(com.jalinyiel.petrichor.core.check.CheckKey)")
    public void requiredKeyFunc() {
    }

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.*.*(..))&&execution(public * *(..))&&args(String, ..)")
    public void countTasks() {
    }

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.ListHandler.*(..))&&execution(public * *(..))&&args(String, ..)")
    public void listTasks() {
    }

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.StringHandler.*(..))&&execution(public * *(..))&&args(String, ..)")
    public void stringTasks() {
    }

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.SetHandler.*(..))&&execution(public * *(..))&&args(String, ..)")
    public void setTasks() {
    }

    @Pointcut("execution(* com.jalinyiel.petrichor.core.handler.MapHandler.*(..))&&execution(public * *(..))&&args(String, ..)")
    public void mapTasks() {
    }

    @Around("requiredKeyFunc()")
    public ResponseResult checkKey(ProceedingJoinPoint pjp) throws Throwable {
        //从方法签名的实参中取出key
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args = pjp.getArgs();
        String key = (String) args[keyIndex];
        //检查key是否存在
        if (!isKeyExist(key)) {
            return ResponseResult.failedResult(CommonResultCode.NOT_FOUND, "key not exist!");
        }
        //检查key是否过期
        else if (isKeyExpire(key)) {
            return ResponseResult.failedResult(CommonResultCode.EXPIRE,"key is expire!");
        }
        return (ResponseResult) pjp.proceed();
    }

    private boolean isKeyExist(String key) {
        return contextUtil.keyExist(key);
    }

    private boolean isKeyExpire(String key) {
        Optional<Long> expireTime = contextUtil.getExpire(key);
        if (!expireTime.isPresent()) {
            return false;
        }
        long t = expireTime.get();
        Instant expireInstant = Instant.ofEpochSecond(t);
        Duration duration = Duration.between(Instant.now(), expireInstant);
        if (duration.isNegative()) {
            long expireT = contextUtil.removeExpireKey(key);
            log.info(String.format("键已经过期%d秒",expireT));
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Instant now = Instant.now();
        Instant nowPlus3Seconds = now.plusSeconds(3);
        Duration duration = Duration.between(nowPlus3Seconds, now);
        System.out.println(String.format("seconds are %s", duration.isNegative()));
    }

    @After("listTasks()")
    public void listTaskCount(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args = joinPoint.getArgs();
        String key = (String) args[keyIndex];
        updateHotSpotStatistics(key);
        contextUtil.updateTaskRecord(TaskType.LIST_TASK, key);
    }

    @After("stringTasks()")
    public void stringTaskCount(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args = joinPoint.getArgs();
        String key = (String) args[keyIndex];
        updateHotSpotStatistics(key);
        contextUtil.updateTaskRecord(TaskType.STRING_TASK, key);
    }

    @After("setTasks()")
    public void setTaskCount(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args = joinPoint.getArgs();
        String key = (String) args[keyIndex];
        updateHotSpotStatistics(key);
        contextUtil.updateTaskRecord(TaskType.SET_TASK, key);
    }

    @After("mapTasks()")
    public void mapTaskCount(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        int keyIndex = IntStream.range(0, paramNames.length)
                .filter(i -> PARAM_NAME_OF_KEY.equals(paramNames[i])).findAny().getAsInt();
        Object[] args = joinPoint.getArgs();
        String key = (String) args[keyIndex];
        updateHotSpotStatistics(key);
        contextUtil.updateTaskRecord(TaskType.MAP_TASK, key);
    }

//    private void updateExpireStatistics(String key) {
//        TreeMap<PetrichorObject, Long> expireStatistics = contextUtil.getExpireList();
//        PetrichorObject keyObject = contextUtil.getKey(key);
//        if(expireStatistics.size() >= contextUtil.getExpireCapacity()) {
//            expireStatistics.stream();
//        }
//        expireStatistics.add(keyObject);
//    }

    private void updateHotSpotStatistics(String key) {
        List<PetrichorObject> hotSpotStatistics = contextUtil.getHotSpotData();
        PetrichorObject keyObject = contextUtil.getKey(key);
        hotSpotStatistics.add(keyObject);
        if(hotSpotStatistics.size() >= contextUtil.getExpireCapacity()) {
           hotSpotStatistics = hotSpotStatistics.stream().sorted(this::compareHotSpotData)
                    .limit(contextUtil.getHotSpotCapacity()).collect(Collectors.toList());
        }
        contextUtil.replaceHotSpotStatistics(hotSpotStatistics);
    }

    private int compareHotSpotData(PetrichorObject p1, PetrichorObject p2) {
        return p1.getCount()-p2.getCount();
    }
}
