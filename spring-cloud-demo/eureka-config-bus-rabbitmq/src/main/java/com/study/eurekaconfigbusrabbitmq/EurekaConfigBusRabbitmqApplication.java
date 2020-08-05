package com.study.eurekaconfigbusrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaConfigBusRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaConfigBusRabbitmqApplication.class, args);
	}

}
