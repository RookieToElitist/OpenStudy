package com.study.shardingjdbc3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.study.shardingjdbc3.mapper")
public class ShardingJdbc3Application {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbc3Application.class, args);
	}

}
