package com.kyle.ie.appliance_status;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplianceStatusServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplianceStatusServiceApplication.class, args);
	}
}
