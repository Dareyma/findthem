package com.spring.core.model;

public class LikeModel {

	private int id_like;
	
	private boolean enabled;
		
	private UserModel user;
	
	private PostModel post;

	public LikeModel() {
		
	}

	public LikeModel(int id_like, boolean enabled, UserModel user, PostModel post) {
		super();
		this.id_like = id_like;
		this.enabled = enabled;
		this.user = user;
		this.post = post;
	}

	public int getId_like() {
		return id_like;
	}

	public void setId_like(int id_like) {
		this.id_like = id_like;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
