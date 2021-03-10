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
@Command(name = "set", description = "[operation set type data]", mixinStandardHelpOptions = true,
        subcommands = {
                SetCommand.Add.class,
                SetCommand.Remove.class,
                SetCommand.Size.class,
                SetCommand.Get.class,
                SetCommand.Intersect.class,
                SetCommand.Union.class,
                SetCommand.Complementary.class
        }
)
public class SetCommand {

    @Component
    @Command(name = "add", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "add new elements to set")
    static class Add implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.SET_ADD.getOpsName(), params, paramClasses, TaskType.SET_TASK));
            System.out.println(responseResult.isSuccess() ? "OK" : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "remove", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "remove elements from set")
    static class Remove implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.SET_REMOVE.getOpsName(), params, paramClasses, TaskType.SET_TASK));
            System.out.println(responseResult.isSuccess() ? "OK" : responseResult.getMsg());
            return 33;
        }
    }

        @Component
        @Command(name = "get", mixinStandardHelpOptions = true,
                exitCodeOnExecutionException = 34, description = "get amount of elements in set")
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
                                        PetrichorTask.of(SupportedOperation.SET_GET.getOpsName(), params, paramClasses, TaskType.SET_TASK));
                        System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
                        return 33;
                }
        }

    @Component
    @Command(name = "size", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "get amount of elements in set")
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
                            PetrichorTask.of(SupportedOperation.SET_SIZE.getOpsName(), params, paramClasses, TaskType.SET_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "inter", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "intersect sets")
    static class Intersect implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.INTERSECT.getOpsName(), params, paramClasses, TaskType.SET_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "union", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "union sets")
    static class Union implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.UNION.getOpsName(), params, paramClasses, TaskType.SET_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }

    @Component
    @Command(name = "comple", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 34, description = "complementary sets")
    static class Complementary implements Callable<Integer> {

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
                            PetrichorTask.of(SupportedOperation.COMPLEMENTARY.getOpsName(), params, paramClasses, TaskType.SET_TASK));
            System.out.println(responseResult.isSuccess() ? responseResult.getData() : responseResult.getMsg());
            return 33;
        }
    }
}

