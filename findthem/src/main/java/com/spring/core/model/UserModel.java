package com.spring.core.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserModel {

	private int id;
	
	@NotEmpty(message = "El nombre de usuario no puede estar en blanco")
	private String username;
	
	@NotEmpty(message = "El nombre no puede estar en blanco")
	private String name;
	
	@NotEmpty(message = "Los apellidos no puede estar en blanco")
	private String surname;
	
	@NotEmpty(message = "La contraseña no debe ser rellenada")
	private String password;
	
	@NotEmpty(message = "El campo email no puede estar en blanco")
	private String email;
	
	private boolean enabled;
	
	@NotNull(message = "Debe poner un número de teléfono")
	private int phone;
	
	public UserModel() {
		
	}

	public UserModel(int id, String username, String name, String surname, String password, String email,
			boolean enabled, int phone) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.phone = phone;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	
}
