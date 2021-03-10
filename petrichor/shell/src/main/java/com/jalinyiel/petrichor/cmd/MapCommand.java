package com.jalinyiel.petrichor.cmd;

import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.task.PetrichorTask;
import com.jalinyiel.petrichor.core.task.SupportedOperation;
import com.jalinyiel.petrichor.core.task.TaskListener;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

import java.util.Map;
import java.util.concurrent.Callable;

@Component
@Command(name = "map", description = "[operation map type data]", mixinStandardHelpOptions = true,
        subcommands = {
                MapCommand.Keys.class,
                MapCommand.Size.class,
                MapCommand.Del.class,
                MapCommand.Put.class,
                MapCommand.Get.class,
                MapCommand.Values.class
        }
)
public class MapCommand {

    @Component
    @Command(name = "put", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "put new k-v pair to map")
    static class Put implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1", description = "the key of pair")
        private String pairKey;

        @Parameters(index = "2", description = "the value of pair")
        private String pairValue;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, String.class, String.class};
            Object[] params = {key, pairKey, pairValue};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.MAP_SET.getOpsName(), params, paramClasses, TaskType.MAP_TASK));
            System.out.println(responseResult.isSuccess() ? "OK" : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "get", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get value of pair in map")
    static class Get implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1", description = "the key of pair")
        private String pairKey;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, String.class};
            Object[] params = {key, pairKey};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.MAP_GET.getOpsName(), params, paramClasses, TaskType.MAP_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "del", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "delete pair in map")
    static class Del implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1", description = "the key of pair")
        private String pairKey;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, String.class};
            Object[] params = {key, pairKey};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.MAP_DELETE.getOpsName(), params, paramClasses, TaskType.MAP_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "size", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get size of the map")
    static class Size implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.MAP_SIZE.getOpsName(), params, paramClasses, TaskType.MAP_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "keys", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get all keys of a map")
    static class Keys implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.MAP_KEYS.getOpsName(), params, paramClasses, TaskType.MAP_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "vals", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get all values of a map")
    static class Values implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.MAP_VALUES.getOpsName(), params, paramClasses, TaskType.MAP_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

}
