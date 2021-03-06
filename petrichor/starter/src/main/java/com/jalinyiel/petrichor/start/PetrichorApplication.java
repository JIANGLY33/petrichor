package com.jalinyiel.petrichor.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication(scanBasePackages = {"com.jalinyiel.petrichor"})
public class PetrichorApplication {

	public static void main(String[] args) {
//		String[] disabledCommands = {"--spring.shell.command.quit.enabled=false"};
//		String[] fullArgs = StringUtils.concatenateStringArrays(args, disabledCommands);
		SpringApplication.run(PetrichorApplication.class, args);
	}

}
