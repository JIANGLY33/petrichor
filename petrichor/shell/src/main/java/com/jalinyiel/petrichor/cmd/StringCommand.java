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

@Component
@Command(name = "string", description = "[operation string type data]", mixinStandardHelpOptions = true,
        subcommands = {
                StringCommand.Set.class,
                StringCommand.Get.class,
                StringCommand.MGet.class
        }
)
public class StringCommand {

    @Component
    @Command(name = "set", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "set new string element")
    static class Set implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1", description = "value")
        private String value;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, String.class};
            Object[] params = {key, value};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.STRING_SET.getOpsName(), params, paramClasses, TaskType.STRING_TASK));
            System.out.println(responseResult.isSuccess() ? "OK" : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "get", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get new string element")
    static class Get implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.STRING_GET.getOpsName(), params, paramClasses, TaskType.STRING_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "mget", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get new string element")
    static class MGet implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0..*", description = "keys")
        private String[] keys;

        @Override
        public Integer call() {
            Class[] paramClasses = {String[].class};
            Object[] params = {keys};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.STRING_MULTIPLE_GET.getOpsName(), params, paramClasses, TaskType.STRING_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }
}
