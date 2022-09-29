package com.bosonit.conecta4v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class Conecta4v2Application {

	public static void main(String[] args) {
		SpringApplication.run(Conecta4v2Application.class, args);
	}

}
