package com.kyle.ie.report.repair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class RepairReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepairReportServiceApplication.class, args);
	}
}
