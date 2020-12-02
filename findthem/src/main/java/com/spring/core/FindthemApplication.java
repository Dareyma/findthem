package com.spring.core;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class FindthemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindthemApplication.class, args);
	}
	
	@Bean
    public DozerBeanMapper studentMapper() {
        return new DozerBeanMapper ();
    }

}
