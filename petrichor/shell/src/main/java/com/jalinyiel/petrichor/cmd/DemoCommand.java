package com.jalinyiel.petrichor.cmd;

import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;


@Component
@Command(name = "demoCommand")
public class DemoCommand implements Callable<Integer> {

    @Parameters
    String str;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message.")
    private boolean helpRequested = false;

    @Override
    public Integer call() {
        System.out.println(String.format(String.format("hello,%s", str)));
        return 33;
    }
}
