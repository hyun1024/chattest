package com.sparta.springsecondwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringsecondworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecondworkApplication.class, args);
	}

}
