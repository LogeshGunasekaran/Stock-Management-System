package com.product_department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
public class ProductDepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDepartmentApplication.class, args);
	}

}
