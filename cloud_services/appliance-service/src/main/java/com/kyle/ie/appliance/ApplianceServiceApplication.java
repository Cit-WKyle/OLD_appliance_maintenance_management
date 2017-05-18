package com.kyle.ie.appliance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplianceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplianceServiceApplication.class, args);
	}
}
