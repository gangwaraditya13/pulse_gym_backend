package com.pluse.gym_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.pluse.gym_admin.repository")
public class GymAdminApplication {

	public static void main(String[] args) {

		SpringApplication.run(GymAdminApplication.class, args);
	}

}
