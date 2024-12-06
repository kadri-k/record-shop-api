package com.northcoders.recordshopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.northcoders.recordshopapi")
public class RecordShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordShopApiApplication.class, args);
	}


}
