//package com.jalinyiel.petrichor.cmd;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.shell.ExitRequest;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.commands.Quit;
//
//@ShellComponent
//public class QuitCommand implements Quit.Command {
//
//    @Autowired
//    private ApplicationContext context;
//
//    @ShellMethod(
//            value = "Exit the shell.",
//            key = {"quit", "exit"},
//            group = "Built-In Commands"
//    )
//    public void quit() {
//        SpringApplication.exit(context);
//        throw new ExitRequest();
//    }
//}
