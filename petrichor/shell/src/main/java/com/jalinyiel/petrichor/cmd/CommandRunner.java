package com.jalinyiel.petrichor.cmd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Component
public class CommandRunner implements CommandLineRunner, ExitCodeGenerator {

    private final IFactory factory; // auto-configured to inject PicocliSpringFactory

    private DemoCommand demoCommand;

    private int exitCode;

    private final String reminder = "petrichor:> ";

    public CommandRunner(DemoCommand demoCommand, IFactory factory) {
        this.demoCommand = demoCommand;
        this.factory = factory;
    }

    @Override
    public void run(String... args) throws Exception {
        CommandLine cmd = new CommandLine(demoCommand,factory);
        // 指定运行策略：只运行最后一个命令
        cmd.setExecutionStrategy(new CommandLine.RunLast());
        cmd.execute("-h");

        System.out.print(reminder);
        Scanner scanner = new Scanner(System.in);
        // 当有下一行时，不断读取下一行输入
        while (scanner.hasNextLine()) {
            // 从scanner读取下一行
            String line = scanner.nextLine().trim();
            // 如果本行为空，忽略
            if (line.length() == 0) {
                System.out.print(reminder);
                continue;
            }
            // 用String.split方法分离参数列表，分隔符为空格和制表符\t
            String[] arguments = line.split("(( +)|(\t+))+");
            // 执行命令
            cmd.execute(arguments);

            // 每行命令执行结束后暂停100毫秒，以免出现输入输出混乱
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            catch (InterruptedException e) {
                System.exit(1);
            }
            System.out.print(reminder);
        }
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
