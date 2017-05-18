package com.kyle.ie.appliance_status;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import com.kyle.ie.appliance_status.verticles.ServerVerticle;

import io.vertx.core.Vertx;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ApplianceStatusNotificationServiceApplication {
	
	@Autowired
	private ServerVerticle _serverVerticle;

	public static void main(String[] args) {
		SpringApplication.run(ApplianceStatusNotificationServiceApplication.class, args);
	}
	
	  @PostConstruct
	  public void deployVerticle() {
	    Vertx vertx = Vertx.vertx();
	    vertx.deployVerticle(_serverVerticle);
	  }
}

/*
get old status and new status
create payload to send over event bus
*/