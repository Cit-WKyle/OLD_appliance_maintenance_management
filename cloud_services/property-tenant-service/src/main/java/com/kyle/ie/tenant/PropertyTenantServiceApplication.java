package com.kyle.ie.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropertyTenantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyTenantServiceApplication.class, args);
	}
}



//enable feign clients, eureka clients, aws config