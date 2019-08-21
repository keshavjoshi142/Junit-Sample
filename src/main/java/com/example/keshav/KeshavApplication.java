package com.example.keshav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@SpringBootApplication
public class KeshavApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeshavApplication.class, args);
	}

}
