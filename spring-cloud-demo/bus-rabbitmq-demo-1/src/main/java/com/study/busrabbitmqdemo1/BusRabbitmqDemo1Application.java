package com.study.busrabbitmqdemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BusRabbitmqDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(BusRabbitmqDemo1Application.class, args);
	}

}
