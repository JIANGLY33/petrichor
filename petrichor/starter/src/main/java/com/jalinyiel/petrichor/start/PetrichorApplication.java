package com.jalinyiel.petrichor.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jalinyiel.petrichor"})
public class PetrichorApplication {

	public static void main(String[] args) {
		// let Spring instantiate and inject dependencies
		System.exit(SpringApplication.exit(SpringApplication.run(PetrichorApplication.class, args)));
		System.out.println("KKKKK");
	}

}
