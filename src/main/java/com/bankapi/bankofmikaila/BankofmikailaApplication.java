package com.bankapi.bankofmikaila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.bankapi.bankofmikaila")
public class BankofmikailaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankofmikailaApplication.class, args);
	}

}
