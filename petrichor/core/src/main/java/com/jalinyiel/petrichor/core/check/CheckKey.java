package com.jalinyiel.petrichor.core.check;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CheckKey {
}
