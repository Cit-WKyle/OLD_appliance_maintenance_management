package com.kyle.ie.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class PropertyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyServiceApplication.class, args);
	}
}
