package com.study.eurekademo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//开启Eureka服务
@EnableEurekaServer
@SpringBootApplication
public class EurekaDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDemo1Application.class, args);
	}

}
