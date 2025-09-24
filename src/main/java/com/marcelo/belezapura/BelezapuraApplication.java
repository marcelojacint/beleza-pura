package com.marcelo.belezapura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BelezapuraApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelezapuraApplication.class, args);
	}

}
