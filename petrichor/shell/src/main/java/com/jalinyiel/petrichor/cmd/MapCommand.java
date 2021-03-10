package com.jalinyiel.petrichor.cmd;

import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

@Component
@Command(name = "map", description = "[operation map type data]", mixinStandardHelpOptions = true,
        subcommands = {
                StringCommand.Set.class,
                StringCommand.Get.class,
                StringCommand.MGet.class
        }
)
public class MapCommand {
}
