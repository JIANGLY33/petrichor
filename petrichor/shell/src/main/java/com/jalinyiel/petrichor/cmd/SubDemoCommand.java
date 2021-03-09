package com.jalinyiel.petrichor.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;

@Command(name = "subCommand")
@Component
public class SubDemoCommand implements Callable<Integer> {

    @Autowired
    @ParentCommand
    RootCommand rootCommand;

    @Parameters(index = "0")
    int a;

    @Parameters(index = "1")
    int b;

    @Override
    public Integer call() {
        System.out.println(String.format("%d", a+b));
        return 33;
    }
}
