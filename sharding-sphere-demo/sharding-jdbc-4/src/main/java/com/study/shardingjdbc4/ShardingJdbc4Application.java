package com.study.shardingjdbc4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.study.shardingjdbc4.mapper")
public class ShardingJdbc4Application {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbc4Application.class, args);
	}

}
