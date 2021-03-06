package com.jalinyiel.petrichor.cmd;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.stream.Collectors;

@ShellComponent
public class DemoCommand {
    @ShellMethod("print")
    public String print(String test, @ShellOption(value = "-str", help = "somebody") String... str) {
        String res = Arrays.stream(str).collect(Collectors.joining());
        return String.format("hello,%s,%s",test,res);
    }

    @ShellMethod("add")
    public int add(int a, int b) {
        return a+b;
    }
}
