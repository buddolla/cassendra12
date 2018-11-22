package com.reavtive.casendra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class SpringCasendraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCasendraApplication.class, args);
	}
}
