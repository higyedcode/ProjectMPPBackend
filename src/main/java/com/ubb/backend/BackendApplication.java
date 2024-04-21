package com.ubb.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableWebMvc
@EnableScheduling
// @EnableJpaRepositories(basePackages = {"com.ubb.backend.repository"})
// @ComponentScan(basePackages = {"com.ubb.backend"})
@EntityScan(basePackages="com.ubb.backend.model")
public class BackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
