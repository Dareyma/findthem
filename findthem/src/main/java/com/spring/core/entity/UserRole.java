package com.spring.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="user_role", uniqueConstraints=@UniqueConstraint(columnNames= {"role", "username" }))
public class UserRole {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_role_id", unique=true, nullable=false)
    private Integer id;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="username", nullable=false)
    private User user;
    
    @Column(name="role", nullable=false, length=45)
    private String role;

	public UserRole(Integer id, User user, String role) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
	}

	public UserRole() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
	
}
