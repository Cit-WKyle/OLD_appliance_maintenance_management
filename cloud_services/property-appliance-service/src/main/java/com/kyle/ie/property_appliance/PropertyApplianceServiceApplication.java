package com.kyle.ie.property_appliance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class PropertyApplianceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyApplianceServiceApplication.class, args);
	}
}
