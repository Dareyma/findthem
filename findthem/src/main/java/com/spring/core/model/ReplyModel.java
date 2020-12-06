package com.spring.core.model;

import javax.validation.constraints.NotEmpty;

public class ReplyModel {
	
	private int id;
	
	@NotEmpty(message = "El texto no puede estar en blanco")
	private String text;
	private String image;
	private String date;
	private UserModel user_id;
	private PostModel post_id;
	
	public ReplyModel() {
		
	}

	public ReplyModel(int id, String text, String image, String date, UserModel  user_id, PostModel post_id) {
		super();
		this.id = id;
		this.text = text;
		this.image = image;
		this.date = date;
		this.user_id = user_id;
		this.post_id = post_id;
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

	public UserModel getUser_id() {
		return user_id;
	}

	public void setUser_id(UserModel user_id) {
		this.user_id = user_id;
	}

	public PostModel getPost_id() {
		return post_id;
	}

	public void setPost_id(PostModel post_id) {
		this.post_id = post_id;
	}
	
}
