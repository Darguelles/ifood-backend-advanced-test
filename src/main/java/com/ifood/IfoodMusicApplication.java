package com.ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
public class IfoodMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfoodMusicApplication.class, args);
	}
}
