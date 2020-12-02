package com.spring.core.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.core.entity.UserRole;

@Repository("userRoleRepository")
public interface UserRoleRepository extends JpaRepository <UserRole, Serializable>{
    public abstract UserRole findByUser (String username);
}
