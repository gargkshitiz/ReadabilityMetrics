package com.test.readability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.test.*")
public class ReadabilityApp {

	public static void main(String[] args) {
		SpringApplication.run(ReadabilityApp.class, args);
	}

}