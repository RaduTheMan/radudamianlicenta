package com.damian.gemixqueapi;

import com.damian.gemixqueapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GemixqueApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(GemixqueApiApplication.class, args);
	}
}
