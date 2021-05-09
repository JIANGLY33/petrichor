package com.jalinyiel.petrichor.cmd;

import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.task.PetrichorTask;
import com.jalinyiel.petrichor.core.task.SupportedOperation;
import com.jalinyiel.petrichor.core.task.TaskListener;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.*;
import java.util.concurrent.Callable;


public class GlobalCommand {

    @Component
    @Command(name = "del", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "delete entry")
    static class Del implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class};
            Object[] params = {key};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.DELETE_KEY.getOpsName(), params, paramClasses, TaskType.GLOBAL_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "time", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get expire time")
    static class Time implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class};
            Object[] params = {key};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.TIME_KEY.getOpsName(), params, paramClasses, TaskType.GLOBAL_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "type", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get type")
    static class Type implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class};
            Object[] params = {key};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.TYPE_KEY.getOpsName(), params, paramClasses, TaskType.GLOBAL_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "expire", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "set expire time for entry")
    static class Expire implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1", description = "expire time, unit is second")
        private long time;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, long.class};
            Object[] params = {key,time};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.EXPIRE_KEY.getOpsName(), params, paramClasses, TaskType.GLOBAL_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "exist", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "check key if exist")
    static class Exist implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class};
            Object[] params = {key};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.KEY_EXIST.getOpsName(), params, paramClasses, TaskType.GLOBAL_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }
}
