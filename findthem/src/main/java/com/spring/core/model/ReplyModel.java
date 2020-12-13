package com.spring.core.model;

import javax.validation.constraints.NotEmpty;

public class ReplyModel {
	
	private int id;
	
	@NotEmpty(message = "El texto no puede estar en blanco")
	private String text;
	private String image;
	private String date;
	private UserModel user;
	private PostModel post;
	
	public ReplyModel() {
		
	}

	public ReplyModel(int id, @NotEmpty(message = "El texto no puede estar en blanco") String text, String image,
			String date, UserModel user, PostModel post) {
		super();
		this.id = id;
		this.text = text;
		this.image = image;
		this.date = date;
		this.user = user;
		this.post = post;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}
	
}
