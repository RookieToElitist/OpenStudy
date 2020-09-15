package com.study.shardingjdbc1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.study.shardingjdbc1.mapper")
public class ShardingJdbc1Application {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbc1Application.class, args);
	}

}
