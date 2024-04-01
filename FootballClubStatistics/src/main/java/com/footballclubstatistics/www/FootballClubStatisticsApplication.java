package com.footballclubstatistics.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.footballclub.core.entity")
public class FootballClubStatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballClubStatisticsApplication.class, args);

	}

}
