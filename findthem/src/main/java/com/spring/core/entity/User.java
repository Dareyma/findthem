package com.spring.core.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty(message = "El nombre de ususario no puede estar en blanco")
	@Column(name="username", unique=true)
	private String username;
	
	@NotEmpty(message = "El nombre no puede estar en blanco")
	@Column(name="name")
	private String name;
	
	@NotEmpty(message = "Los apellidos no pueden estar en blanco")
	@Column(name="surname")
	private String surname;
	
	@NotEmpty(message = "La contraseña no puede estar en blanco")
	@Column(name="password")
	private String password;
	
	@NotEmpty(message = "El email no puede estar en blanco")
	@Column(name="email")
	private String email;
	
	//@Size(min = 9, max = 9)
	@Column(name="phone")
	private int phone;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
	private Set<UserRole> user_Role = new HashSet<UserRole>();

	public User() {
		
	}

	public User(int id, String username, String name, String surname, String password, String email, int phone,
			boolean enabled, Set<UserRole> user_Role) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.user_Role = user_Role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUser_Role() {
		return user_Role;
	}

	public void setUser_Role(Set<UserRole> user_Role) {
		this.user_Role = user_Role;
	}
	
	
	
}
