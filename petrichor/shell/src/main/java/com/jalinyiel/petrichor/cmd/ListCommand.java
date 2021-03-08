package com.jalinyiel.petrichor.cmd;

import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.task.PetrichorTask;
import com.jalinyiel.petrichor.core.task.SupportedOperation;
import com.jalinyiel.petrichor.core.task.TaskListener;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Component
@Command(name = "listCommand", subcommands = {
        ListCommand.RPush.class, ListCommand.LLength.class
})
public class ListCommand  {

    @Component
    @Command(name = "rpush", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34)
    static class RPush implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Option(names = "-k", description = "key")
        private String key;

        @Option(names = "-v", arity = "1..*", description = "values")
        private String[] values;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, String[].class};
//            List<String> paramList = new LinkedList<>(asList(key));
//            List<String> valueList = Arrays.stream(values).collect(Collectors.toList());
//            paramList.addAll(valueList);
//            Object params = paramList.toArray();
            Object[] params = {key,values};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.RIGHT_PUSH.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            return 33;
        }
    }

    @Component
    @Command(name = "len", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34)
    static class LLength implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(description = "key")
        private String key;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class};
            Object[] params = new Object[]{key};
            ResponseResult responseResult = taskListener.process(
                    PetrichorTask.of(SupportedOperation.LIST_LENGTH.getOpsName(),
                            params, paramClasses, TaskType.LIST_TASK)
            );
            System.out.println(responseResult.getData());
            return 33;
        }
    }
}
