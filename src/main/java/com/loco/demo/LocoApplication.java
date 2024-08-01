package com.loco.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class LocoApplication {

	public static void main(String[] args) {
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());

		SpringApplication.run(LocoApplication.class, args);
	}

}
