package com.xdman.temporalpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TemporalPocApplication {

	public static final String TASK_QUEUE = "orders";

	public static void main(String[] args) {
		SpringApplication.run(TemporalPocApplication.class, args);
	}

}
