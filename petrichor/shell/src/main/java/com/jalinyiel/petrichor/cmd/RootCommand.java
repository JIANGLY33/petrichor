package com.jalinyiel.petrichor.cmd;

import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;


@Component
@Command(name = "Petrichor", subcommands = {ListCommand.class})
public class RootCommand implements Callable<Integer> {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message.")
    private boolean helpRequested = false;

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
