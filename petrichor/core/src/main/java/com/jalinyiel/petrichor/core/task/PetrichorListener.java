package com.jalinyiel.petrichor.core.task;

interface PetrichorListener<T> {

    T process(PetrichorTask petrichorTask);
}
