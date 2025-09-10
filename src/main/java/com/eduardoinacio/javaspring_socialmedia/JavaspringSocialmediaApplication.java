package com.eduardoinacio.javaspring_socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableScheduling
public class JavaspringSocialmediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaspringSocialmediaApplication.class, args);
	}

}
