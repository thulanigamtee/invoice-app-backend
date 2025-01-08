package com.invoice.invoice_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class InvoiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceApiApplication.class, args);
	}

}
