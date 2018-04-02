package com.berkum.spring.paging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootPagingExampleApp 
{
	public static void main(String[] args) {
		SpringApplication.run(SpringBootPagingExampleApp.class, args);
	}
}
