package com.jalinyiel.petrichor.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.jalinyiel.petrichor"})
@EnableScheduling
public class PetrichorApplication {

	public static void main(String[] args) {
		// let Spring instantiate and inject dependencies
		System.exit(SpringApplication.exit(SpringApplication.run(PetrichorApplication.class, args)));
		System.out.println("KKKKK");
	}

}
