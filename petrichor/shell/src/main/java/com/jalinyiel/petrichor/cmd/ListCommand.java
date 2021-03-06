package com.jalinyiel.petrichor.cmd;

import com.jalinyiel.petrichor.core.ResponseResult;
import com.jalinyiel.petrichor.core.task.PetrichorTask;
import com.jalinyiel.petrichor.core.task.SupportedOperation;
import com.jalinyiel.petrichor.core.task.TaskListener;
import com.jalinyiel.petrichor.core.task.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class ListCommand {

    @Autowired
    TaskListener taskListener;

    @ShellMethod("rpush")
    public String rightPush(@ShellOption(value = "-key") String key, @ShellOption(value = "-v") String... values) {
        Class[] paramClasses = {String.class, String[].class};
        List<String> paramList = List.of(key);
        paramList.addAll(Arrays.stream(values).collect(Collectors.toList()));
        Object[] params = paramList.toArray();
        ResponseResult responseResult =
                taskListener.process(
                        PetrichorTask.of(SupportedOperation.RIGHT_PUSH.getOpsName(), params, paramClasses, TaskType.LIST_TASK));
        return responseResult.getData().toString();
    }

    @ShellMethod("len")
    public String listLength(@ShellOption(value = "-key") String key) {
        Class[] paramClasses = {String.class};
        Object[] params = {key};
        ResponseResult responseResult = taskListener.process(
                PetrichorTask.of(SupportedOperation.LIST_LENGTH.getOpsName(), params, paramClasses, TaskType.LIST_TASK)
        );
        return responseResult.getData().toString();
    }
}
