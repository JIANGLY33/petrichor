package com.jalinyiel.petrichor.cmd;

import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;


@Component
@Command(name = "root", subcommands = {ListCommand.class})
public class DemoCommand {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message.")
    private boolean helpRequested = false;


}
