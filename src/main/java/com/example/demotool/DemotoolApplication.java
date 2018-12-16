package com.example.demotool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demotool.dao")
public class DemotoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemotoolApplication.class, args);
	}
}
