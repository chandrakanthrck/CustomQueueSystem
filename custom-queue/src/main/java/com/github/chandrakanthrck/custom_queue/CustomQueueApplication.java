package com.github.chandrakanthrck.custom_queue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CustomQueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomQueueApplication.class, args);
	}

}
