package com.jalinyiel.petrichor.cmd;

import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;


@Component
@Command(name = "Petrichor", subcommands = {
        ListCommand.class,
        StringCommand.class,
        SetCommand.class,
        MapCommand.class,
        GlobalCommand.Del.class,
        GlobalCommand.Time.class,
        GlobalCommand.Exist.class,
        GlobalCommand.Type.class,
        GlobalCommand.Expire.class
})
public class RootCommand implements Callable<Integer> {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message.")
    private boolean helpRequested = false;

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
