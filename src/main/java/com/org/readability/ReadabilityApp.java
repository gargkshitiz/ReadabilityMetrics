package com.org.readability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * Spring boot app launcher
 * 
 * @author Kshitiz Garg
 */
@SpringBootApplication
@ComponentScan(basePackages="com.org.*")
@EntityScan("com.org.readability.model")
@EnableJpaRepositories("com.org.readability.repo")
public class ReadabilityApp extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ReadabilityApp.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ReadabilityApp.class, args);
	}

}