package com.footballclubapplication.www;

import com.footballclubapplication.www.producer.CustomProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Properties;


@SpringBootApplication
@ComponentScan(basePackages = {"com.footballclub.core", "com.footballclubapplication.www"})
@EnableJpaRepositories(basePackages = {"com.footballclub.core.repository"})
@EntityScan("com.footballclub.core.entity")
@EnableAspectJAutoProxy
public class FootballClubApplication {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public CustomProducer customProducer() {
		return new CustomProducer(bootstrapServers);
	}

	@Bean
	public Producer<?,?> myProducer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServers);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return new KafkaProducer<String, String>(
				props
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(FootballClubApplication.class, args);
	}

}
