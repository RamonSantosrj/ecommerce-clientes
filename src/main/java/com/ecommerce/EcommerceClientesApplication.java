package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EcommerceClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceClientesApplication.class, args);
	}

}
