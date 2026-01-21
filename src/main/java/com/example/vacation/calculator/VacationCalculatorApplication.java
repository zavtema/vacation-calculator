package com.example.vacation.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan // Гарантия того, что properties подхватятся корректно
public class VacationCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacationCalculatorApplication.class, args);
	}

}
