package com.NorthKingSys.jbf.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.NorthKingSys.jbf.cn.mapper")
public class NorthKingSysApplication {
	public static void main(String[] args) {
		SpringApplication.run(NorthKingSysApplication.class, args);
	}
}
