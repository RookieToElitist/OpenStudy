package com.study.eurekasleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//开启Eureka服务
@EnableEurekaServer
@SpringBootApplication
public class EurekaSleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaSleuthApplication.class, args);
	}

}
