package com.basic.Basic_Project_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class BasicProjectSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicProjectSpringApplication.class, args);
	}

}
