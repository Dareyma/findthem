package com.spring.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.core.entity.UserRole;
import com.spring.core.repository.UserRoleRepository;
import com.spring.core.service.UserRoleService;
@Service
public class UserRoleServiceImpl implements UserRoleService {
    
    @Autowired
    @Qualifier("userRoleRepository")
    private UserRoleRepository userRoleRepository;
    
    @Override
    public List<UserRole> getListUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
	public UserRole addUser(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}


}