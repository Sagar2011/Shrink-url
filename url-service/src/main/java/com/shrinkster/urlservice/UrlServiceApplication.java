package com.shrinkster.urlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableScheduling
@SpringBootApplication
public class UrlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlServiceApplication.class, args);

	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
