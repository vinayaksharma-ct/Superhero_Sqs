package com.example.LocalSQS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SqsApplicationLocal {

	public static void main(String[] args) {
		SpringApplication.run(SqsApplicationLocal.class, args);
	}

}
