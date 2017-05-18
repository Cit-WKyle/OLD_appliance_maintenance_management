package com.kyle.ie.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
public class AnalyticsWebPortalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsWebPortalServiceApplication.class, args);
	}
}
