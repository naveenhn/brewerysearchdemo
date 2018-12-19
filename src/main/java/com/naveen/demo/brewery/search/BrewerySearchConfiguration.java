/**
 * 
 */
package com.naveen.demo.brewery.search;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author naveen
 *
 */
@Configuration
public class BrewerySearchConfiguration {
	
	@Bean
	public RestTemplate RestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
