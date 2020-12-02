package com.spring.core.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCrypt {
	
	public String generator(String contraseña) {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        System.out.println(pe.encode("admin"));
        return pe.encode(contraseña);
    }
    
    public String encrypt(String pass) {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        return pe.encode(pass);
    }
}
