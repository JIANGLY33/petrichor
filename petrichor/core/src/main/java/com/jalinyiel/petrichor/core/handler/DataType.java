package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.task.TaskType;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DataType {

    TaskType type();
}
