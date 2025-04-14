package com.college.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories("com.college.bus.repository")
@SpringBootApplication
public class BusTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusTrackingApplication.class, args);
	}

}
