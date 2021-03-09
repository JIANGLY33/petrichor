package com.jalinyiel.petrichor.cmd;

import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.task.PetrichorTask;
import com.jalinyiel.petrichor.core.task.SupportedOperation;
import com.jalinyiel.petrichor.core.task.TaskListener;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import picocli.CommandLine.*;


public class GlobalCommand {

    @Component
    @Command(name = "del", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34)
    static class Del implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @CommandLine.Parameters(index = "0", description = "key")
        private String key;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class};
            Object[] params = {key};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.DELETE_KEY.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            System.out.println(responseResult.isSuccess() ? "OK" : responseResult.getMsg());
            return 33;
        }
    }
}
