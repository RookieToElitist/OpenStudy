package com.study.sleuthdemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SleuthDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SleuthDemo2Application.class, args);
	}

}
