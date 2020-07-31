package com.study.zipkinsleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

//开启
@EnableZipkinServer
@SpringBootApplication
public class ZipkinSleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinSleuthApplication.class, args);
	}

}
