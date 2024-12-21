package com.abc.bank.mortgage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.abc.bank.mortgage")
//@EnableJpaRepositories(basePackages = "com.abc.bank.mortgage.repository")
@OpenAPIDefinition(info = @Info(title = "Mortgage API", version = "v1"))
public class MortgageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortgageApplication.class, args);
	}

}
