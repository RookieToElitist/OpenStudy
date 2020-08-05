package com.study.configbusrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigBusRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigBusRabbitmqApplication.class, args);
	}

}
