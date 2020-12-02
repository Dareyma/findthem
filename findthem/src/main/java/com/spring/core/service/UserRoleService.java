package com.spring.core.service;

import java.util.List;

import com.spring.core.entity.UserRole;

public interface UserRoleService {
	
	//listar usuarios
    public abstract List<UserRole> getListUsers();
    
    //añadir usuario
    public abstract UserRole addUser(UserRole user_Role);
}
