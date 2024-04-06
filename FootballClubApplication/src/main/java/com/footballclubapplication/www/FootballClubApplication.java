package com.footballclubapplication.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.footballclub.core", "com.footballclubapplication.www"})
@EnableJpaRepositories(basePackages = {"com.footballclub.core.repository"})
@EntityScan("com.footballclub.core.entity")
public class FootballClubApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballClubApplication.class, args);
	}

}
