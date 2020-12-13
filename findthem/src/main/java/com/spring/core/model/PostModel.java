package com.spring.core.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostModel {
	
	private int post_id;
	
	@NotEmpty()
	private String title;
	
	@NotEmpty(message = "El texto no puede estar en blanco")
	private String text;
	
	@NotNull(message = "Debe contener algún tipo")
	private int type;
	
	private String image;
	
	@NotEmpty(message = "La localización no puede estar en blanco")
	private String location;
	
	private String date;
	
	private UserModel user;
	
	public PostModel() {
		
	}

	public PostModel(int post_id, String title,String text,int type, String image, String location, String date,
			UserModel user) {
		super();
		this.post_id = post_id;
		this.title = title;
		this.text = text;
		this.type = type;
		this.image = image;
		this.location = location;
		this.date = date;
		this.user = user;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
}
