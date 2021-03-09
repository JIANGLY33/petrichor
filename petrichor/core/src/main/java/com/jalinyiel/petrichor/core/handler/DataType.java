package com.jalinyiel.petrichor.core.handler;

import com.jalinyiel.petrichor.core.task.TaskType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataType {

    TaskType type();
}
