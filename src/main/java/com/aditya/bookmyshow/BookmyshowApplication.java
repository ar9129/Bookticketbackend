package com.aditya.bookmyshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BookmyshowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmyshowApplication.class, args);
	}

}
