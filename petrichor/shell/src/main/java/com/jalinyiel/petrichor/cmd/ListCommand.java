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
@Command(name = "list", description = "[operation list type data]", mixinStandardHelpOptions = true, subcommands = {
        ListCommand.RPush.class,
        ListCommand.LPush.class,
        ListCommand.Insert.class,
        ListCommand.Index.class,
        ListCommand.Range.class,
        ListCommand.Trim.class,
        ListCommand.LPop.class,
        ListCommand.RPop.class,
        ListCommand.LLength.class
})
public class ListCommand {

    @Component
    @Command(name = "rpush", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "push new element to list from right")
    static class RPush implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1..*", arity = "1..*", description = "values")
        private String[] values;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, String[].class};
            Object[] params = {key, values};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.RIGHT_PUSH.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            System.out.println(responseResult.isSuccess() ? "OK" : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "lpush", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34,description = "push new element to list from left")
    static class LPush implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1..*", arity = "1..*", description = "values")
        private String[] values;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, String[].class};
            Object[] params = {key, values};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.LEFT_PUSH.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            System.out.println(responseResult.isSuccess() ? "OK" : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "insert", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "insert new element to specific position")
    static class Insert implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Option(names = {"-b,-before"})
        private boolean isBefore = true;

        @Parameters(index = "1", description = "value")
        private String value;

        @Parameters(index = "2", description = "the position to insert")
        private int pivot;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, boolean.class, int.class, String.class};
            Object[] params = {key, isBefore, pivot, value};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.LIST_INSERT.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            System.out.println(responseResult.isSuccess() ? "OK" : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "index", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get specific position element")
    static class Index implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1", description = "the index of element")
        private int index;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, int.class};
            Object[] params = {key, index};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.LIST_INDEX.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "range", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get elements in range(left-closed and right-open)")
    static class Range implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1", arity = "0..1", description = "the start index of scope")
        private int start = 0;

        @Parameters(index = "2", arity = "0..1", description = "the end index of scope")
        private int end = -1;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, int.class, int.class};
            Object[] params = {key, start, end};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.LIST_RANGE.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "lpop", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "remove element from left")
    static class LPop implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.LEFT_POP.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "rpop", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "remove element from right")
    static class RPop implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.RIGHT_POP.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "set", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "resign specific index element in list")
    static class Set implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1", description = "new element")
        private String value;

        @Parameters(index = "2", description = "the index of old element")
        private int index;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, String.class, int.class};
            Object[] params = {key, value, index};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.LIST_SET.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "trim", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34,description = "remain elements in rangee(left-closed and right-open)")
    static class Trim implements Callable<Integer> {

        @Autowired
        TaskListener taskListener;

        @Parameters(index = "0", description = "key")
        private String key;

        @Parameters(index = "1", arity = "0..1", description = "the start index of scope")
        private int start = 0;

        @Parameters(index = "2", arity = "0..1", description = "the start index of scope")
        private int end = -1;

        @Override
        public Integer call() {
            Class[] paramClasses = {String.class, int.class, int.class};
            Object[] params = {key, start, end};
            ResponseResult responseResult =
                    taskListener.process(
                            PetrichorTask.of(SupportedOperation.LIST_TRIM.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "len", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get the length of list")
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
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }
}
