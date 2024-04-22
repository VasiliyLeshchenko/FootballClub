package com.footballclubstatistics.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.footballclub.core", "com.footballclubstatistics.www"})
@EnableJpaRepositories(basePackages = {"com.footballclub.core.repository"})
@EntityScan("com.footballclub.core.entity")
@EnableAspectJAutoProxy
public class FootballClubStatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballClubStatisticsApplication.class, args);

	}

}
