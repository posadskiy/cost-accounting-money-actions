package com.posadskiy.costaccounting.moneyactions.web;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class MoneyActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyActionApplication.class, args);
	}

}
