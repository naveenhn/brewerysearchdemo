package com.naveen.demo.brewery.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BrewerySearchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrewerySearchDemoApplication.class, args);
	}
	
	
	

}

